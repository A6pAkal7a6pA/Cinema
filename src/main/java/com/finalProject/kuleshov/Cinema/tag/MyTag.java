package com.finalProject.kuleshov.Cinema.tag;


import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLUserDao;
import com.finalProject.kuleshov.Cinema.dto.User;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class MyTag extends TagSupport {
    private static final Logger LOG = Logger.getLogger(MyTag.class);


    private String login;

    public String getLogin() {
        return login;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        MySQLUserDao userDao = new MySQLUserDao();
        try {
            User user = userDao.selectUserByLogin(login);

            if (user.getRole().equals(User.ROLE.ADMIN)) {
                String userName = user.getFirstName();

                out.print("admin: " + userName);
            } else {
                String userLogin = user.getLogin();
                out.print(userLogin);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        return SKIP_BODY;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
