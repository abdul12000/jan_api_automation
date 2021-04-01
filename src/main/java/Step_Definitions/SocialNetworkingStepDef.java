package Step_Definitions;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.core.Is;
import utilities.RequestBodyServices;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SocialNetworkingStepDef extends BaseSteps {

    Response responseForGetServiceCall, responseForGetCommentCall, responseForPostCommentCall;
    DocumentContext requestBody;
    RequestBodyServices requestBodyServices;

    @Given("service is up and running")
    public void service_is_up_and_running() {
        setHeadersWithContentType();
        setEndpointPath(serviceUrl);
        getCall();
        responseForGetServiceCall = getResponse();
        assertThat(responseForGetServiceCall.statusCode(), Is.is(200));

    }

    @When("i search for {string} of a comment with a GET method")
    public void i_search_for_of_a_comment_with_a_get_method(String id) {
        setHeadersWithContentType();
        setEndpointPath(makeCommentEndpoint + id);
        getCall();
        responseForGetCommentCall = getResponse();
    }

    @Then("i should get the correct {string}, {string}, {string} and {string} returned with status code of {int}")
    public void i_should_get_the_correct_and_returned_with_status_code_of(String id, String name, String email, String body, Integer sCode) {
        assertThat(responseForGetCommentCall.statusCode(), Is.is(sCode));
        assertThat(responseForGetCommentCall.body().jsonPath().get("id"), is(Integer.parseInt(id)));
        assertThat(responseForGetCommentCall.body().jsonPath().get("name"), is(name));
        assertThat(responseForGetCommentCall.body().jsonPath().get("email"), is(email));
        assertThat(responseForGetCommentCall.body().jsonPath().get("body"), is(body));
    }


    @When("I create a new comment with the following details {string},{string}, {string} and {string},")
    public void i_create_a_new_comment_with_the_following_details_and(String postId, String name, String email, String body) {
        setHeadersWithContentType();
        setEndpointPath(makeCommentEndpoint);
        requestBodyServices = new RequestBodyServices();

        requestBody = loadJsonTemplate(MakeACommentPayload);
        requestBodyServices.setRequestBodyForNewComment(requestBody, postId, name, email, body);

        getPostCall();
        responseForPostCommentCall = getResponse();

    }

    @Then("i should get the correct {string},{string}, {string} and {string} returned and with status code of {int}")
    public void i_should_get_the_correct_and_returned_and_with_status_code_of(String postId, String name, String email, String body, Integer sCode) {
        assertThat(responseForPostCommentCall.statusCode(), is(sCode));
        assertThat(responseForPostCommentCall.body().jsonPath().get("postId"), is(postId));
        assertThat(responseForPostCommentCall.body().jsonPath().get("name"), is(name));
        assertThat(responseForPostCommentCall.body().jsonPath().get("email"), is(email));
        assertThat(responseForPostCommentCall.body().jsonPath().get("body"), is(body));

    }


}
