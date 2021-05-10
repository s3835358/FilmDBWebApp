import io.javalin.Javalin;
import controllers.*;
import io.javalin.core.util.Header;
import services.ShowService;
import util.Config;

class Main {

    public static void main(String[] args) {

        // Load the configuration

        Config.loadConfig();

        Javalin app = Javalin.create().start(Config.APPLICATION_PORT);

        // Account Management

        app.post("/login", AccountController::login);
        app.post("/register", AccountController::register);

        // Information Getters

        app.get("/get-pcos", InformationController::getAllPcos);
        app.get("/get-genres", InformationController::getAllGenres);

        // Admin Routes

        app.post("/admin/get-account-requests", AdminController::getAccountRequests);
        app.post("/admin/approve-account-request", AdminController::approveAccountRequest);
        app.post("/admin/reject-account-request", AdminController::rejectAccountRequest);
        app.post("/admin/get-pending-shows", AdminController::getPendingShows);
        app.post("/admin/approve-pending-show", AdminController::approvePendingShow);
        app.post("/admin/reject-pending-show", AdminController::rejectPendingShow);
        app.post("/admin/delete-show", AdminController::deleteShow);

        // Add a Show

        app.post("/show/add", ShowController::addShow);




        // Error 404

        app.error(404, ctx -> {
            ctx.status(200);
            ctx.result("{\"success\": false}");
        });

        // Set some properties such as headers, etc.

        app.before(ctx -> {

            // Set JSON header for all requests

            ctx.contentType("application/json");

            // Set CORS to * so that everyone can access the API

            ctx.header(Header.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            ctx.header(Header.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, OPTIONS");
            ctx.header(Header.ACCESS_CONTROL_ALLOW_HEADERS, "*");

            ShowService.processAutoApprovals();

        });

    }

}