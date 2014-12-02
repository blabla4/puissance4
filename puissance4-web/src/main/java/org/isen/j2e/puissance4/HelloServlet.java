package org.isen.j2e.puissance4;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    public Game game = new Game();
    public int i = 1;
    java.util.Date date= new java.util.Date();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(/*game.getBoard().getBoardAsText()*/new Timestamp(date.getTime()));
        out.flush();
        out.close();
        //this.getServletContext().getRequestDispatcher( "/WEB-INF/app.jsp" ).forward( req, resp );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        StringBuffer sb = new StringBuffer();

        try
        {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (Exception e) { e.printStackTrace(); }

        JSONParser parser = new JSONParser();
        JSONObject joUser = null;
        try {
            joUser = (JSONObject) parser.parse(sb.toString());
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        int column = Integer.valueOf((String) joUser.get("name"));
        try {
            if(i % 2 == 0) {
                game.play(Coin.BLUE, column);
            } else {
                game.play(Coin.RED, column);
            }
            i = i + 1;
        } catch (GameException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        if(game.getWinner() != null) {
            out.write("GAGNE !!");
            game = new Game();
        } else {
            out.write(game.getBoard().getBoardAsText());
        }
        out.flush();
        out.close();
    }
}