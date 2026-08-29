package controllers;

import io.ebean.DB;
import io.ebean.PagedList;
import models.Message;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.filters.csrf.CSRF;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.add;
import views.html.index;
import views.html.listpage;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Form<SampleForm> form;
    private final Form<Message> messageForm;
    private final MessagesApi messagesApi;
    @Inject
    public HomeController(FormFactory formFactory, MessagesApi messagesApi){
        this.form = formFactory.form(SampleForm.class);
        this.messageForm = formFactory.form(Message.class);
        this.messagesApi = messagesApi;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index(Http.Request request) {
        return ok(views.html.index.render(
                "メッセージを入力してください", form, request, messagesApi.preferred(request)));
    }



    public Result send(Http.Request request){
        if (CSRF.getToken(request).isEmpty()) {
            return forbidden("CSRF token required");
        }
        final Form<SampleForm> f = form.bindFromRequest(request);
        if(!f.hasErrors()) {
            SampleForm data = f.get();
            String msg = "you typed: " + data.getMessage();
            return redirect(routes.HomeController.index()).flashing("success", msg);
        }else{
            return badRequest(index.render("ERROR", f, request, messagesApi.preferred(request)));
        }
    }

    public Result list(){
        int page = 0;
        int pagesize = 5;

        // https://github.com/playframework/play-java-ebean-example/blob/2.6.x/app/controllers/HomeController.java
        PagedList<Message> list = DB.find(Message.class).orderBy("id")
                .setFirstRow(page * pagesize).setMaxRows(pagesize).findPagedList();
        return ok(listpage.render(list));
    }

    public Result add(Http.Request request){
        return ok(views.html.add.render(
                "please input", messageForm, request, messagesApi.preferred(request)));
    }

    public Result create(Http.Request request){
        if (CSRF.getToken(request).isEmpty()) {
            return forbidden("CSRF token required");
        }
        final Form<Message> f = messageForm.bindFromRequest(request);
        if(!f.hasErrors()){
            Message data = f.get();
            data.save();
            return redirect("/add");
        }else{
            return badRequest(add.render(
                    "ERROR", f, request, messagesApi.preferred(request)));
        }
    }

}
