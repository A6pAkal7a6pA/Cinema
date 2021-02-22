package com.finalProject.kuleshov.cinema.tag;

import com.finalProject.kuleshov.cinema.dao.mysql.MySQLUserDao;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CopyrightTag extends TagSupport {
    private static final Logger LOG = Logger.getLogger(CopyrightTag.class);

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("Create by Max Kuleshov");
        } catch (IOException e) {
            LOG.error("Trouble with doStartTag: " + e.getMessage());
        }
        return SKIP_BODY;
    }
}
