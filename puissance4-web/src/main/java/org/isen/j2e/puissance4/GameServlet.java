package org.isen.j2e.puissance4;

import org.json.simple.JSONArray;
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
import java.io.StringWriter;


@WebServlet("/game")
public class GameServlet extends HttpServlet {
    public Game game = new Game();
    public int i = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        game = new Game();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("ok");
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
        } catch (Exception e) { }

        JSONParser parser = new JSONParser();
        JSONObject data = null;
        try {
            data = (JSONObject) parser.parse(sb.toString());
        } catch (Exception e) { }

        int column = Integer.valueOf((String) data.get("column"));
        try {
            if(i % 2 == 0) {
                game.play(Coin.BLUE, column);
            } else {
                game.play(Coin.RED, column);
            }
            i = i + 1;
        } catch (GameException e) { }

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        if(game.getWinner() != null) {
            out.write(game.getWinner().toString());
            game = new Game();
        } else {
            JSONArray list = new JSONArray();
            for (int i=0; i<game.getBoard().getRowNumber(); i++) {
                JSONArray list2 = new JSONArray();
                for (int j=0; j<game.getBoard().getColNumber(); j++) {
                    try {
                        list2.add(game.getBoard().getCoin(j+1, i+1).toString());
                    } catch (Exception e) {
                        list2.add(" ");
                    }
                }
                list.add(list2);
            }
            StringWriter out2 = new StringWriter();
            list.writeJSONString(out2);
            String jsonText = out2.toString();
            out.write(jsonText);
        }
        out.flush();
        out.close();
    }
}