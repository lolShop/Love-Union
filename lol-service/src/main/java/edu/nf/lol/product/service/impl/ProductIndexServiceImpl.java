package edu.nf.lol.product.service.impl;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.product.dao.ProductIndexDao;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductDto;
import edu.nf.lol.product.entity.PageBean;
import edu.nf.lol.product.entity.ProductType;
import edu.nf.lol.product.service.AdminProductTypeService;
import edu.nf.lol.product.service.ProductIndexService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Crazy
 * @date 2020/4/1
 */
@Service("productIndexService")
@Transactional
public class ProductIndexServiceImpl implements ProductIndexService {
    @Autowired
    private ProductIndexDao productIndexDao;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    private AdminProductTypeService AdminProductTypeService;
    @Cacheable(cacheNames = "productType",key = "#parentId")
    @Override
    public List<ProductType> listProductType(Integer parentId) {
        List<ProductType> list=productIndexDao.listProductType(parentId);
        return list;
    }
    @Override
    public void productAll() {
        List<Product> list=productIndexDao.productAll();
        list.forEach(product -> {
            ProductDto productDto= new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            productDto.setProductMainImage(product.getProductMainImage());
            productDto.setProductTypeName(getTypeName(product.getProductTypeId()));
            productDto.setSpecsPrice(product.getProductSpecsList().get(0).getSpecsPrice());
            productDto.setPromotionPrice(product.getProductSpecsList().get(0).getPromotionPrice());
            productDto.setProductCreateTime(product.getProductCreateTime());
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setObject(productDto);
            template.index(indexQuery);
        });
    }
    @Override
    public PageBean<ProductDto> productSearch(Integer pageNum, Integer pageSize, String productName) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(productName).field("productName").field("productTypeName"))
                .withPageable(PageRequest.of(pageNum-1,pageSize))
                .withHighlightFields(new HighlightBuilder.Field("productName").preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        PageBean pageBean=new PageBean();
        pageBean.setList(getAggregatedPage(searchQuery));
        pageBean.setPageSize(pageSize);
        pageBean.setRows(pageSize(productName));
        return pageBean;
    }

    public  Integer pageSize(String productName){
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(productName).field("productName").field("productTypeName"))
                .withHighlightFields(new HighlightBuilder.Field("productName").preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        return getAggregatedPage(searchQuery).size();
    }

    @Override
    public  PageBean<ProductDto> listProductPrice(Integer pageNum, Integer pageSize,String descName,SortOrder sortOrder,String productName) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(productName).field("productName").field("productTypeName"))
                .withPageable(PageRequest.of(pageNum-1,pageSize))
                .withSort(new FieldSortBuilder(descName).order(sortOrder))
                .withHighlightFields(new HighlightBuilder.Field("productName").preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        PageBean pageBean=new PageBean();
        pageBean.setList(getAggregatedPage(searchQuery));
        pageBean.setPageSize(pageSize);
        pageBean.setRows(pageSize(productName));
        return pageBean;
    }

    /**
     * 这个是ES 高亮搜索方法
     * @param searchQuery
     * @return
     */
    public  List<ProductDto> getAggregatedPage(SearchQuery searchQuery){
        AggregatedPage<ProductDto> page = template.queryForPage(searchQuery, ProductDto.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<ProductDto> list = new ArrayList<>();
                //循环所有命中记录
                for (SearchHit searchHit : response.getHits()) {
                    //获取命中的所有字段信息
                    Map<String, Object> source = searchHit.getSourceAsMap();
                    ProductDto productDto = new ProductDto();
                    //获取id
                    productDto.setProductId(Integer.valueOf(searchHit.getId()));
                    productDto.setProductMainImage(source.get("productMainImage").toString());
                    productDto.setProductName(source.get("productName").toString());
                    productDto.setProductTypeName(source.get("productTypeName").toString());
                    productDto.setSpecsPrice( new BigDecimal(source.get("specsPrice").toString()));
                    productDto.setPromotionPrice(new BigDecimal(source.get("promotionPrice").toString()));
                    //封装高亮字段信息
                    HighlightField highlightField = searchHit.getHighlightFields().get("productName");
                    if(highlightField != null){
                        productDto.setProductName(highlightField.fragments()[0].toString());
                    }
                    list.add(productDto);
                }
                return new AggregatedPageImpl(list);
            }
            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
                return null;
            }
        });
        return page.getContent();
    }
    @Override
    public String getTypeName(Integer id){
        List<ProductType> list=AdminProductTypeService.listProductTypeId(id);
      return getTypeNameList("",list).substring(1);
    }
    public  String getTypeNameList(String s,List<ProductType> list){
        String typeName=s;
        for (ProductType productType : list) {
            typeName+=getTypeNameList(typeName,productType.getProductTypeList())+"\\"+productType.getProductTypeName();
        }
        return typeName;
    }
}