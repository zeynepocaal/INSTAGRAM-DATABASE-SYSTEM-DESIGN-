
package homework3;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static User[] users;
    public static void main(String[] args) throws InterruptedException {
        int order=0;
        users= new User[3];
        users[order]= new User("yasemin", "yasemin", "1234",
                "12 ocak 1099", 'k', "0553333333", "ysmn@gmail.com",
                true, 10,10);
        order++;
        //  System.out.println(  );
        users[order]= new User("ahmet", "ahmet", "1234",
                "12 ocak 1099", 'E', "0553333333", "ahmet@gmail.com",
                true, 10,10);
        order++;
        users[order]= new User("berk", "berk", "1234",
                "12 ocak 1099", 'E', "0553333333", "berk@gmail.com",
                true, 10,10);
        order++;
      //  loginMain(users);
       /* users[0].login("yasemin","1234");
        users[1].login("ahmet","1234");
        users[2].login("berk","1234");*/
        users[1].sendMessage("merhaba",users[0]);
        users[1].sendMessage("yeniden merhaba",users[0]);
       users[0].sendMessage("merhaba",users[1]);
        users[1].sendMessage("yeniden merhaba",users[0]);

        users[0].follow(users[2]);
        users[0].follow(users[1]);
        users[1].follow(users[0]);
        users[1].follow(users[2]);
        users[2].follow(users[0]);

       // users[1].follow(users[2]);
       // users[0].follow(users[1]);


       users[0].sharePosts("bu benim ilk postum");
        users[2].sharePosts("bu benim ilk postum");
        users[1].sharePosts("denemepost4");

        users[2].sharePosts("denemepost1");
        users[2].sharePosts("denemepost3");
        users[1].sharePosts("denemepost2");
       users[1].sharePosts("denemepost4");
        users[0].sendComment("İlk defa böyle güzel bir yazı gördüm.",0);
        users[1].sendComment("İlk defa böyle güzel bir yazı gördüm.",1);
        users[1].sendComment("İlk defa böyle güzel bir yazı gördüm.",2);
        users[0].like(0);
        users[1].like(0);
        users[2].like(0);
        users[0].likeComment(0,0);
        users[0].dislikeComment(1,0);
        users[1].seeWhoLiked(0);
        users[1].seeWhoDisliked(0);
        loginMain(users);
     //  users[1].readMessage();

     //   users[0].login("yasemin","1234");
      //  users[2].writeAttributes();

       // users[0].seePosts();
    //    users[0].readMessage(0);

     //   users[2].writeAttributes();
       // users[1].seePosts(0);
    // users[1].readMessage(0);

     /*  yasemin.sendMessage("merhaba",ahmet);

       ahmet.readMessage(0);*/

 }
    public static void loginMain(User[] users) throws InterruptedException {

        Scanner input= new Scanner(System.in);
        System.out.print("username: ");
        String userName= input.next();
        System.out.print("password: ");
        String password=input.next();

      /*  String userName="ahmet";
        String password="1234";*/

        int registered=0;
        for (int i=0; i<users.length; i++){
            if(userName.equals(users[i].getUserName()) && password.equals(users[i].getPassword())){
                users[i].login(userName,password);
                //users[i].follow(users[i+1]);
              //  users[i].sendMessage("hello",users[i+1]);
                registered++;
            }
        }
        if(registered==0){
            System.out.println("Wrong username or password. Please try again");
            loginMain(users);
        }
    }
    
}
