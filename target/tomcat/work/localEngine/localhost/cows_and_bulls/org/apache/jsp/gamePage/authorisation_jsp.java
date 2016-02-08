package org.apache.jsp.gamePage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class authorisation_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Игра \"Быки и Коровы\"</title>\r\n");
      out.write("    </head>\r\n");
      out.write(" \r\n");
      out.write("    <body>\r\n");
      out.write("\t\t<H1>Логическая игра \"Быки и Коровы\"</H1>\r\n");
      out.write("\r\n");
      out.write("\t\t<p>Кратие правила игры: <br/>\r\n");
      out.write("\t\t\tПравила предельно просты: участники, игрок (Вы) и компьютер загадываете по четырехзначному числу ХХХХ (число может быть любым). <br/>\r\n");
      out.write("\t\t\tЗадача - отгадать загаданное соперником число, передавая противнику числа-запросы и получая ответы в виде быков и коров. Ходы делаются по очереди.<br/>\r\n");
      out.write("\t\t\t<br/>\r\n");
      out.write("\t\t\tПример:<br/>\r\n");
      out.write("\t\t\tСоперником загадано число \"1234\" (Вы его не знаете)<br/>\r\n");
      out.write("\t\t\tВы передаете ему произвольное число, допустим \"3246\". Соперник сравнивает свое загаданное число с Вашим произвольно запрошенным и выдает Вам <br/>\r\n");
      out.write("\t\t\tколичество быков и коров, где <br/>\r\n");
      out.write("\t\t\tКОРОВА - цифра в Вашем произвольном числе, которая также присутствует и в загаданном числе, НО ее позиция - не совпадает с загаданной.<br/>\r\n");
      out.write("\t\t\tБЫК - цифра в Вашем произвольном числе, которая также присутствует и в загаданном числе И ее позиция - совпадает с загаданной.<br/>\r\n");
      out.write("\t\t\tВ нашем случае, для числа 3246 ответом будет: 2 коровы (это цифры 3 и 4), 1 бык (цифра 2).<br/>\r\n");
      out.write("\t\t\t<br/>\r\n");
      out.write("\t\t\tПриведу еще несколько примеров загаданных чисел, например:<br/>\r\n");
      out.write("\t\t\tзагадано число 4333, переданные числа-запросы:<br/>\r\n");
      out.write("\t\t\t1234 - 1 бык(3), 1 корова(4). Цифры в скобках даны для понимания, в игре они не даются.<br/>\r\n");
      out.write("\t\t\t3321 - 1 бык(3), 1 корова(3). Цифры в скобках даны для понимания, в игре они не даются.<br/>\r\n");
      out.write("\t\t\t4333 - 4 быка. Число отгадано.\r\n");
      out.write("\t\t</p>\r\n");
      out.write("\t\t<H4>PS: Обращаю внимание, что если количество быков или коров будет введено неверно хотя бы один раз, Ваше число будет отгадано неверно или не будет отгадано вовсе. <br/>\r\n");
      out.write("        \tПоэтому будьте внимательны. Если Вы выиграли, проверьте все Ваши предыдущие ходы на правильность. Алгоритму требутеся не более 12(максимум, в среднем - 8..10) ходов, чтобы разгадать любое загаданное Вами число</H4>\r\n");
      out.write("\r\n");
      out.write("\t\t<H4>История ходов:</H4>\r\n");
      out.write("\r\n");
      out.write("\t\t<p>Для старта игры, введите Ваше игровое имя: </p>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<form action=\"/cows_and_bulls/gamePage\" method=\"get\">\r\n");
      out.write("\t\t\t<input type=\"text\" name=\"login\"/> - Login <br>\r\n");
      out.write("\t\t\t<input type=\"submit\" value=\"Start game\">\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\r\n");
      out.write("    </body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
