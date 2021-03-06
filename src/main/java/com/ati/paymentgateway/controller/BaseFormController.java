package com.ati.paymentgateway.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.ServletContextAware;

/**
 * Implementation of <strong>SimpleFormController</strong> that contains
 * convenience methods for subclasses.  For example, getting the current
 * user and saving messages/errors. This class is intended to
 * be a base class for all Form controllers.
 * <p/>
 * <p><a href="BaseFormController.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:ab.annas@gmail.com">Andi Baso Annas</a>
 */
public class BaseFormController implements ServletContextAware {
    protected final transient Log log = LogFactory.getLog(getClass());
    public static final String MESSAGES_KEY = "successMessages";

    protected String templateName = "accountCreated.vm";
    protected String cancelView;
    protected String successView;

    public MessageSourceAccessor messages;
    private ServletContext servletContext;
    
	
    @Autowired
    public void setMessages(MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }


    @SuppressWarnings("unchecked")
    public void saveError(HttpServletRequest request, String error) {
        List<Object> errors = (List<Object>) request.getSession().getAttribute("errors");
        if (errors == null) {
            errors = new ArrayList<Object>();
        }
        errors.add(error);
        request.getSession().setAttribute("errors", errors);
    }

    @SuppressWarnings("unchecked")
    public void saveMessage(HttpServletRequest request, String msg) {
        List<String> messages = (List<String>) request.getSession().getAttribute(MESSAGES_KEY);

        if (messages == null) {
            messages = new ArrayList<String>();
        }

        messages.add(msg);
        request.getSession().setAttribute(MESSAGES_KEY, messages);
    }

    /**
     * Convenience method for getting a i18n key's value.  Calling
     * getMessageSourceAccessor() is used because the RequestContext variable
     * is not set in unit tests b/c there's no DispatchServlet Request.
     *
     * @param msgKey
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, Locale locale) {
        return messages.getMessage(msgKey, locale);
    }

    /**
     * Convenient method for getting a i18n key's value with a single
     * string argument.
     *
     * @param msgKey
     * @param arg
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, String arg, Locale locale) {
        return getText(msgKey, new Object[]{arg}, locale);
    }

    /**
     * Convenience method for getting a i18n key's value with arguments.
     *
     * @param msgKey
     * @param args
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, Object[] args, Locale locale) {
        return messages.getMessage(msgKey, args, locale);
    }

//    /**
//     * Convenience method to get the Configuration HashMap
//     * from the servlet context.
//     *
//     * @return the user's populated form from the session
//     */
//    public Map getConfiguration() {
//        Map config = (HashMap) servletContext.getAttribute(Constants.CONFIG);
//
//        // so unit tests don't puke when nothing's been set
//        if (config == null) {
//            return new HashMap();
//        }
//
//        return config;
//    }

    /**
     * Set up a custom property editor for converting form inputs to real objects
     *
     * @param request the current request
     * @param binder  the data binder
     */
/*    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        NumberFormat numberFormat =  NumberFormat.getNumberInstance(request.getLocale());
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class,numberFormat, true));
        binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
        binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
        binder.registerCustomEditor(byte[].class,  new ByteArrayMultipartFileEditor());
    	binder.registerCustomEditor(List.class, "coreLimitReferenceDetails", new CustomCollectionEditor(List.class)
        {
          @Override
          protected Object convertElement(Object element)
          {
              Long id = null;

              if(element instanceof String && !((String)element).equals("")){
                  //From the JSP 'element' will be a String
                  try{
                      id = Long.parseLong((String) element);
                  }
                  catch (NumberFormatException e) {
                      System.out.println("Element was " + ((String) element));
                      e.printStackTrace();
                  }
              }
              else if(element instanceof Long) {
                  //From the database 'element' will be a Long
                  id = (Long) element;
              }
//              
//              try{
//              System.out.println(coreLimitReferenceManager);
//              CoreLimitReference coreLimitReference = coreLimitReferenceManager.get(id);
//              }catch(Exception e){
//            	  e.printStackTrace();
//            	  System.out.println(e.getCause());
//            	  e.getMessage();
//            	  
//              }
              return id != null ? coreLimitReferenceManager.get(id) : null;
          }
        });

        SimpleDateFormat dateFormat =  new SimpleDateFormat(getText("date.format", request.getLocale()));
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
    }*/

    /**
     * Convenience message to send messages to users, includes app URL as footer.
     *
     * @param user the user to send a message to.
     * @param msg  the message to send.
     * @param url  the URL of the application.
     */
//    protected void sendUserMessage(CoreUser user, String msg, String url) {
//        if (log.isDebugEnabled()) {
//            log.debug("sending e-mail to user [" + user.getEmail() + "]...");
//        }
//
//        //message.setTo(user.getFullName() + "<" + user.getEmail() + ">");
//        message.setTo(user.getFirstName() + "<" + user.getEmail() + ">");
//
//        Map<String, Serializable> model = new HashMap<String, Serializable>();
//        model.put("user", user);
//
//        // WebWork, then figure it out here too.  In the meantime, the Username
//        // and Password labels are hard-coded into the template. 
//        // model.put("bundle", getTexts());
//        model.put("message", msg);
//        model.put("applicationURL", url);
//        mailEngine.sendMessage(message, templateName, model);
//    }
//
//    @Autowired
//    public void setMailEngine(MailEngine mailEngine) {
//        this.mailEngine = mailEngine;
//    }

/*    @Autowired
    public void setMessage(SimpleMailMessage message) {
        this.message = message;
    }*/

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public final BaseFormController setCancelView(String cancelView) {
        this.cancelView = cancelView;
        return this;
    }

    public final String getCancelView() {
        // Default to successView if cancelView is invalid
        if (this.cancelView == null || this.cancelView.length() == 0) {
            return getSuccessView();
        }
        return this.cancelView;
    }

    public final String getSuccessView() {
        return this.successView;
    }

    public final BaseFormController setSuccessView(String successView) {
        this.successView = successView;
        return this;
    }

/*    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }*/

    protected ServletContext getServletContext() {
        return servletContext;
    }

//    @ExceptionHandler( {AccessDeniedException.class})
//    public String unAuthenticatedException(AccessException ex, HttpServletRequest request) {
//        log.debug(ex);
//        saveError(request, getText("security.unAuthenticatedException", request.getLocale()));
//        return "redirect:/mainMenu";
//    }

    @ExceptionHandler( {AccessDeniedException.class})
    public String authorizationException(AccessDeniedException ex, HttpServletRequest request) {
        log.debug(ex);
        saveError( request, ex.getMessage());
        return "redirect:/403";
    }

    @ExceptionHandler( {Exception.class})
    public String exception(Exception ex, HttpServletRequest request) {
        log.debug(ex);
        ex.printStackTrace();
        saveError( request, ex.getMessage());
        return "redirect:/500";
    }

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		
	}
}
