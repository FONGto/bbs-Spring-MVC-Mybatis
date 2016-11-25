package com.fong2.taglib;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class PublicationTime extends TagSupport{
	 	private static final long serialVersionUID = 1L;
	 	private Date time=new Date();
	 
	    public Date getTime() {
	        return time;
	    }

	    public void setTime(Date time) {
	        this.time = time;
	    }


	    @Override
	    public int doStartTag() throws JspException {
	        JspWriter out = this.pageContext.getOut();
	        try {
	            out.print(PublicationTimeFormat.format(time));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return TagSupport.SKIP_BODY;
	    }

	    @Override
	    public int doEndTag() throws JspException {
	        return EVAL_PAGE;
	    }

	    @Override
	    public void release() {
	        super.release();
	    }
}
