## 包名约定  这是业务层的模块
edu.nf.lol.模块名称.service

edu.nf.lol.comment.service
edu.nf.lol.order.service
edu.nf.lol.shopCart.service
edu.nf.lol.product.service
edu.nf.lol.user.service

然后每个    service 文件夹都要有impl文件夹和service接口 impl文件夹面放class类 
然后在test包中  SpringBootInitializer类中@MapperScan中的dao包名是数组等你们建好了自己加上去即可 
测试都在service这个文件夹测试
