import java.io.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ShoppingCart")
public class ShoppingCart1 extends HttpServlet {
    private static final String[] ITEMS = {
        "Samsung A30", "MI 108 CM Android TV", 
        "HP USB Pen Drive 32GB", "DELL Core i5, 8GB RAM, 1TB HDD Laptop"
    };

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<center><h3>ABC SHOPPING CART</h3></center><hr>");

        HttpSession session = request.getSession();
        int[] purchases = (int[]) session.getAttribute("purchases");
        if (purchases == null) {
            purchases = new int[ITEMS.length];
            session.setAttribute("purchases", purchases);
        }

        if (request.getParameter("checkout") != null) {
            out.println("<h1>Thanks for ordering!</h1>");
        } else {
            if (request.getParameter("add") != null) {
                addPurchases(request, purchases);
                out.println("<h1>Purchase added. Please continue.</h1>");
            } else if (request.getParameter("clear") != null) {
                clearPurchases(purchases);
                out.println("<h1>Cart cleared. Please select items.</h1>");
            } else {
                out.println("<h1>Please Select Your Items!</h1>");
            }
            renderForm(out, request.getRequestURI());
        }
        showPurchases(out, purchases);
        out.close();
    }

    private void addPurchases(HttpServletRequest request, int[] purchases) {
        for (int i = 0; i < ITEMS.length; i++) {
            String quantity = request.getParameter(ITEMS[i]);
            if (quantity != null && !quantity.isEmpty()) {
                purchases[i] += Integer.parseInt(quantity);
            }
        }
    }

    private void clearPurchases(int[] purchases) {
        for (int i = 0; i < purchases.length; i++) {
            purchases[i] = 0;
        }
    }

    private void renderForm(PrintWriter out, String requestURI) {
        out.println("<form method='POST' action='" + requestURI + "'>");
        for (String item : ITEMS) {
            out.println("Quantity <input name='" + item + "' value='0' size='3'> of: " + item + "<br>");
        }

        out.println("<p><input type='submit' name='add' value='Add To Cart'>");
        out.println("<input type='submit' name='checkout' value='Check Out'>");
        out.println("<input type='submit' name='clear' value='Clear Cart'></form>");
    }

    private void showPurchases(PrintWriter out, int[] purchases) {
        out.println("<hr><h2>Your Shopping Basket</h2>");
        for (int i = 0; i < ITEMS.length; i++) {
            if (purchases[i] > 0) {
                out.println(purchases[i] + " x " + ITEMS[i] + "<br>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Shopping Cart Servlet";
    }
}
