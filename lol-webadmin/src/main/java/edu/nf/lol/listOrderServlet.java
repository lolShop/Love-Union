package edu.nf.lol;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.order.AdminService.AdminOrderService;
import edu.nf.lol.order.AdminService.impl.AdminOrderServiceImpl;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.vo.ResponseVO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list_order")
public class listOrderServlet extends BaseController {


    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageN = req.getParameter("pageNum");
        String pageS = req.getParameter("pageSize");
        int pageNum = (pageN != null && !pageN.isEmpty()) ? Integer.parseInt(pageN) : 1;
        int pageSize = (pageS != null && !pageS.isEmpty()) ? Integer.parseInt(pageS) : 10;
        AdminOrderService service = new AdminOrderServiceImpl();
        PageInfo<OrderInfo> pageInfo = service.pageOrder(pageNum, pageSize);
        ResponseVO vo = success(pageInfo);
        resp.setContentType("application/json;charset=utf-8");
    }
}

