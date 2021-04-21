import io.javalin.Javalin;
import controllers.*;
import io.javalin.core.util.Header;
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

        // Add a Show

        app.post("/show/add", ShowController::addShow);

        // Set some properties such as headers, etc.

        app.before(ctx -> {

            // Set JSON header for all requests

            ctx.contentType("application/json");

            // Set CORS to * so that everyone can access the API

            ctx.header(Header.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        });

    }

}