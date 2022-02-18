/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class User {
    private String name;
    private String userName;
    private String birthday;
    private String password;
    private char gender;
    private String phoneNumber;
    private String email;
    private boolean Online;
    private int maxFollowerCount, maxFollowedCount;
    private int followerIndex=0;
    private int followedIndex=0;
    private ArrayList<User> followers = new ArrayList<>();
    private ArrayList<User> followedPeople = new ArrayList<>();
    private ArrayList<Message> inbox = new ArrayList<>();
    private ArrayList<Posts> feed = new ArrayList<>();
    private int postsCount;
    private int messageCount;


    public User (String name,
                 String userName, String password,
                 String birthday, char gender,
                 String phoneNumber, String email, boolean Online,
                 int maxFollowerCount, int maxFollowedCount){
        this.name=name;
        this.userName=userName;
        this.password=password;
        this.birthday=birthday;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.Online=Online;
        this.maxFollowerCount=maxFollowerCount;
        this.maxFollowedCount=maxFollowedCount;
        //writeAttributes();
    }
    public void writeAttributes() throws InterruptedException {
        System.out.println(" name: "+ this.name);
        System.out.println(" user name: "+ this.userName);
        System.out.println(" password : "+ this.password);
        System.out.println(" birthday: "+ this.birthday);
        System.out.println(" gender: "+ this.gender);
        System.out.println(" phone number: "+ this.phoneNumber);
        System.out.println(" email: "+ this.email);
        System.out.println(" is online : "+ this.Online);
        System.out.println("\n\n-----FOLLOWERS-----");
        for(int i = 0; i< this.followers.size(); i++){
            if(this.followers.get(i) !=null){
              //  System.out.println("yaz");
                System.out.println("  "+ this.followers.get(i).getUserName());
            }
        }
        System.out.println();
        System.out.println("\n\n-----FOLLOWED-----");
        for(int i = 0; i< this.followedPeople.size(); i++){
            if(this.followedPeople.get(i) !=null){
                //  System.out.println("yaz");
                System.out.println("  "+ this.followedPeople.get(i).getUserName());
            }
        }
        System.out.println("\n");
        this.menuOfUser();
    }
    //accessor and mutator
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getOnline() {
        return Online;
    }
    public void setOnline(){
        this.Online=Online;
    }
    private Scanner input= new Scanner(System.in);
    public void chooseOption() throws InterruptedException {
        System.out.println("\nPlease choose an option.");
        int goToPage= input.nextInt();
        if(goToPage==2){
            this.readMessage();
        }
        else if(goToPage==3){
            this.readMessage();
        }
        else if(goToPage==4){
            this.writeAttributes();
        }
        else if(goToPage==5){
            this.seePosts();
        }
        else if(goToPage==6){
            System.out.println("\n\n-----FOLLOWERS-----");
            for(int i = 0; i< this.followers.size(); i++){
                if(this.followers.get(i) !=null){
                    //  System.out.println("yaz");
                    System.out.println("  "+ this.followers.get(i).getUserName());
                }
            }
            System.out.println();
            menuOfUser();
        }
        else if(goToPage==7){
            logOut();
        }
    }

    public void logOut() throws InterruptedException {
       // Main m= new Main();
        this.Online=false;
        Main.loginMain(Main.users);
    }
    public boolean login(String userName, String password) throws InterruptedException {
        if(userName.equals(this.userName)&& password.equals(this.password)){
         System.out.println("\nloading ... \n");
        // Thread.sleep(2000) ;
         this.Online=true;
       //  this.seePosts();
          this.menuOfUser();
        }
        else{
            this.Online=false;
        }
        return this.Online;
    }
    public void follow(User u){
        u.followers.add(this);
        this.followedPeople.add(u);
    }


    public boolean sendMessage(String messageContent,  User receiver ){
        Message m= new Message(messageContent,this,receiver);
        return m.sendMessage();
    }

    public boolean receiverMessage(Message m){
        this.inbox.add(m);
        System.out.println("Message has sent succesfully");
        return true;
    }
    public boolean readMessage() throws InterruptedException {
        System.out.println("//////// NEW MESSAGES//////");
        for(int i = 0; i< this.inbox.size(); i++){
            System.out.println( i +" -->  "+ this.inbox.get(i).getSender().userName+ " : "+ this.inbox.get(i).getMessageContent());
       }
        if(this.inbox.size()==0){
            System.out.println("You have no new message\n\n");
            this.menuOfUser();
            return false;
        }
       else {
            do {
                System.out.println("Which one do you want to read?");
                int choosing = input.nextInt();
                if(choosing>=0 && choosing<this.inbox.size()) {
                   this.deleteMessage(choosing);
                   return true;
                }
            }while(true);
       }
    }

    public void deleteMessage(int select) throws InterruptedException {
        if(select<this.inbox.size() && this.inbox.size()>0 && select>=0){
            System.out.println("You've opened this message: \n" + this.inbox.get(select).getMessageContent());
            String feedBack;

            do {
                   Thread.sleep(3000);
                   System.out.println("Did you read it? (yes or no)");
                   feedBack= input.next();
                 //feedBack = JOptionPane.showInputDialog("Did you read it? (yes or no)");
                   if (feedBack.equals("yes")) {
                       System.out.println("----Deleted----");
                       reply(this.inbox.get(select),"Do you want to reply?");
                       this.inbox.get(select).setSeen();
                       this.inbox.remove(select);
                       System.out.println(this.inbox.size());
                   }
            } while (!feedBack.equals("yes"));
        }
        for(int i=0; i<this.inbox.size();i++){
            System.out.println( " -->  "+this.inbox.get(i).getSender().userName+ " : "+ this.inbox.get(i).getMessageContent());
        }
        this.menuOfUser();
    }
    public String reply(Message sendAMessage,String question) throws InterruptedException {

        Scanner newInput= new Scanner(System.in);
        String reply,answer;
        System.out.println(question);
        reply= input.next();
        if (reply.equals("yes")) {
            System.out.println("What do you want to say?");
            answer=newInput.nextLine();
           // this.sendMessage(answer, sendAMessage.getSender());
            try{
                if(this.sendMessage(answer, sendAMessage.getSender()) && !answer.equals("")){
                  //  System.out.println("Message sent successfully.\n");
                }
            }
            catch(Exception error){
                System.out.println("Message could not be sent.");
            }
        }
        else if(reply.equals("no")){
           // this.login(this.userName,this.password);
        }
        else{
            System.out.println("Please check your answer.");
            return reply(sendAMessage,"Do you want to reply?");
        }
        return "";
    }

    public void menuOfUser() throws InterruptedException {
        System.out.println("--------------\uD83D\uDD0D SEARCH \uD83D\uDD0D----------------⚙️SETTINGS ⚙️-------------\uD83D\uDD14 NOTIFICATIONS \uD83D\uDD14------------------\uD83D\uDCE7 MESSAGES \uD83D\uDCE7-------------");
        System.out.println("|                  0              |              1                 |             2                  |              3                 | ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------  \n");
        System.out.println("--------------\uD83D\uDC64 PROFILE \uD83D\uDC64-------------------\uD83C\uDFB4 FEED \uD83C\uDFB4-------------------\uD83D\uDC65 FOLLOWERS \uD83D\uDC65--------------------- \uD83D\uDCF4 LOG OUT \uD83D\uDCF4------------");
        System.out.println("|                  4              |              5             |                6                |                  7                 |");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------       ");
        this.chooseOption();
    }
    public ArrayList getFeed(){
        return this.feed;
    }

    public boolean sharePosts(String postContent){
        Date time= new Date();
        Posts p= new Posts(postContent,this, this.followers, time);
        return  p.sharePost();
    }

    public void timeLine(){
        int postNumber=0;
        Date time= new Date();
        for(int i = this.feed.size()-1; i>=0 ; i--){
            if (time.getDay()- this.feed.get(i).getTime().getDay() < 5) {
                postNumber++;
                if(this.feed.get(i).getSender().gender=='E' || this.feed.get(i).getSender().gender=='e' ){
                    System.out.print("   " + "\uD83D\uDEB9" +" "+ this.feed.get(i).getSender().userName + " : "  );
                }
                else if(this.feed.get(i).getSender().gender=='K' ||this.feed.get(i).getSender().gender=='k' ){
                    System.out.print("   " + "🚺 "+ this.feed.get(i).getSender().userName + " : "  );
                }
                System.out.print(" \uD83D\uDD52");
                if(time.getDay()- this.feed.get(i).getTime().getDay() < 1) {
                    if ( time.getHours() - this.feed.get(i).getTime().getHours() > 0 && time.getMinutes() - this.feed.get(i).getTime().getMinutes() > 0) {
                        System.out.println(" " + (time.getHours() - this.feed.get(i).getTime().getHours()) + " hours " + (time.getMinutes() - this.feed.get(i).getTime().getMinutes()) + " minutes ago ");
                    } else if ( time.getHours() - this.feed.get(i).getTime().getHours() > 0) {
                        System.out.println(" " + (time.getHours() - this.feed.get(i).getTime().getHours()) + " hours ago ");
                    } else if ( time.getMinutes() - this.feed.get(i).getTime().getMinutes() > 0) {
                        System.out.println(" " + (time.getMinutes() - this.feed.get(i).getTime().getMinutes()) + " minutes ago ");
                    } else{
                        System.out.println(" " +"Just now");
                    }
                }
                if(time.getDay()- this.feed.get(i).getTime().getDay() >= 1) {
                    System.out.println("   on "+this.feed.get(i).getTime().getDay()+" at " +  this.feed.get(i).getTime().getHours() + " : " +this.feed.get(i).getTime().getMinutes());
                }
                System.out.println( "\n         " +this.feed.get(i).getPostContent()+"\n");
                System.out.println("        \uD83D\uDC4D "+this.feed.get(i).getLikes().size()+ "   \uD83D\uDC4E "+ this.feed.get(i).getDislikes().size()+ "   \uD83D\uDCAC "+this.feed.get(i).getComments().size());
                System.out.println("        *****************");
                for(int j=0; j<this.feed.get(i).getComments().size();j++){
                    System.out.println("          "+this.feed.get(i).getComments().get(j).getSender().userName+ " : "+this.feed.get(i).getComments().get(j).getCommentContent() +" \uD83D\uDC4D "+ this.feed.get(i).getComments().get(j).getLikes().size()+ "   \uD83D\uDC4E "+ this.feed.get(i).getComments().get(j).getDislikes().size());
                }
                System.out.println("\n ---------------------------------");
            }
        }
    }
    public void seePosts() throws InterruptedException {
        System.out.println("/*/*/*/*/*/*/*/*/*/*/*/ FEED /*/*/*/*/*/*/*/*/*/*/*/\n");
        timeLine();
        System.out.println();
        menuOfUser();
    }
    public boolean sendComment(String commentContent, int num ){
        if(num>=0 && num < this.feed.size()){
            Comments comment= new Comments(this, this.feed.get(num),commentContent);
            return comment.sendComment();
        }
        else{
            System.out.println("ERROR REQUEST NUMBER, PLEASE TRY AGAIN FOR COMMENT");
            return false;
        }
    }
    public boolean like(int num){
        if(num>=0 && num < this.feed.size()){
            return this.feed.get(num).getLikes().add(this);
        }
        else{
            System.out.println("ERROR REQUEST NUMBER, PLEASE TRY AGAIN FOR COMMENT");
            return false;
        }
    }
    public boolean dislike(int num){
        if(num>=0 && num < this.feed.size()){
            return this.feed.get(num).getDislikes().add(this);
        }
        else{
            System.out.println("ERROR REQUEST NUMBER, PLEASE TRY AGAIN FOR COMMENT");
            return false;
        }
    }
    public void likeComment(int numPost, int numComment){
        if(numPost>=0 && numPost < this.feed.size() && numComment>=0 && numComment<this.feed.get(numPost).getComments().size()){
             this.feed.get(numPost).getComments().get(numComment).getLikes().add(this);
        }
        else{
            System.out.println("ERROR REQUEST NUMBER, PLEASE TRY AGAIN FOR COMMENT");
        }
    }
    public void dislikeComment(int numPost, int numComment){
        if(numPost>=0 && numPost < this.feed.size() && numComment>=0 && numComment<this.feed.get(numPost).getComments().size()){
            this.feed.get(numPost).getComments().get(numComment).getDislikes().add(this);
        }
        else{
            System.out.println("ERROR REQUEST NUMBER, PLEASE TRY AGAIN FOR COMMENT");
        }
    }
    public void seeWhoLiked(int num){
        if(num>=0 && num < this.feed.size()){
            System.out.println("People which liked this:");
            for(int i=0; i<this.feed.get(num).getLikes().size(); i++){
                System.out.println(this.feed.get(num).getLikes().get(i).userName);
            }

        }
        else{
            System.out.println("ERROR REQUEST NUMBER, PLEASE TRY AGAIN FOR COMMENT");
        }
    }
    public void seeWhoDisliked(int num){
        if(num>=0 && num < this.feed.size()){
            System.out.println("People which disliked this:");
            for(int i=0; i<this.feed.get(num).getDislikes().size(); i++){
                System.out.println(this.feed.get(num).getDislikes().get(i).userName);
            }

        }
        else{
            System.out.println("ERROR REQUEST NUMBER, PLEASE TRY AGAIN FOR COMMENT");
        }
    }
}
