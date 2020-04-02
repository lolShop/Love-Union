$(function () {
        $.ajax({
            url:"admin/productType/product_type_all",
            method:"get",
            success:function (result) {
                productType("productType",result.data)
            }
        })
    function productType(IdName,list) {
        if (list==""){
            alert("空")
        }
        $.each(list,function (index,type) {
            $("#"+IdName+"").append("<ul class='nav nav-subnav'>" +
                "                <li id="+type.productTypeId+" class='nav-item"+productTypeClass(type.productTypeList)+"'><a href='#!'>"+type.productTypeName+"</a></li>" +
                "              </ul>")
            if (productTypeClass(type.productTypeList)==""){
                return true;
            }
            productType(type.productTypeId,type.productTypeList);
        })
    }
    $("#productType").on("click","li",function (event) {
        $(this).toggleClass('open')
        event.stopPropagation();
    })
    function productTypeClass(list) {
        var  className=" nav-item-has-subnav"
        if (list==""){
            return "";
        }
        return className;
    }
})