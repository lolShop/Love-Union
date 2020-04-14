package edu.nf.lol.product.service;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.product.entity.PageBean;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductDto;
import edu.nf.lol.product.entity.ProductType;
import org.elasticsearch.search.sort.SortOrder;


import java.util.List;

/**
 * @author Crazy
 * @date 2020/4/1
 */
public interface ProductIndexService {
    /**
     * 把数据库的数据添加到ES中
     */
   void productAll();
    PageBean<ProductDto> productSearch(Integer pageNum, Integer pageSize, String productName);
    List<ProductType>  listProductType(Integer parentId );
    /**
     * 这是用来es 价格排序搜索的
     */
    PageBean<ProductDto>  listProductPrice(Integer pageNum, Integer pageSize,String descName, SortOrder sortOrder,String productName);

    /**
     * 这是遍历商品类型的名称递归方法
     * @param Id
     * @return
     */
    String getTypeName(Integer Id);

}