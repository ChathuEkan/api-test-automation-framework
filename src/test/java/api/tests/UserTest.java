package api.tests;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest extends BaseTest
{
  Faker faker;
  User userpayload;
  @BeforeClass
  public void setup(){
      faker = new Faker();
      userpayload = new User();
      userpayload.setId(faker.idNumber().hashCode());//generate random id when use hashCode
      userpayload.setUsername(faker.name().username());
      userpayload.setFirstName(faker.name().firstName());
      userpayload.setLastName(faker.name().lastName());
      userpayload.setEmail(faker.internet().safeEmailAddress());
      userpayload.setPassword(faker.internet().password(5,10));
      userpayload.setPhone(faker.phoneNumber().cellPhone());
  }

  @Test(priority = 1)
  public void testPostUser(){
    Response response = UserEndPoints.createUser(userpayload);
    response.then().log().all().statusCode(200);
    System.out.println(userpayload.getUsername());

      }

  @Test(priority = 2)
  public void testGetUserByName(){
    System.out.println("User: "+this.userpayload.getUsername());
    Response response = UserEndPoints.readUser(this.userpayload.getUsername());
    response.then().log().all().statusCode(200);
  }

  @Test(priority = 3)
  public void testUpdateUserByName(){

    //Update data using payload
    userpayload.setFirstName(faker.name().firstName());
    userpayload.setLastName(faker.name().lastName());
    userpayload.setEmail(faker.internet().safeEmailAddress());

    Response response = UserEndPoints.updateUser(userpayload,this.userpayload.getUsername());
    response.then().log().all().statusCode(200);
  }

  @Test(priority = 4)
  public void testDeleteUserByName(){
    Response response = UserEndPoints.deleteUser(this.userpayload.getUsername());
    response.then().log().all().statusCode(200);
  }
}
