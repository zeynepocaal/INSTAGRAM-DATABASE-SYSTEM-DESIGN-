
package homework3;
import java.util.ArrayList;
import java.util.Date;

public class Posts {
    private String postContent;
    private Date time = new Date();
   private User sender;
    private ArrayList<User> people;
    private ArrayList<Comments> postComments= new ArrayList<>();
    private ArrayList<User> likes= new ArrayList<>();
    private ArrayList<User> dislikes= new ArrayList<>();

    public Posts(String postContent, User sender, ArrayList<User> canSeePosts, Date postTime){
        this.postContent=postContent;
        this.sender=sender;
        this.time= postTime;
       this.people=canSeePosts;
    }
    public String getPostContent(){
        return this.postContent;
    }
    public boolean sharePost(){
       for (int i = 0; i< this.people.size(); i++) {
            this.people.get(i).getFeed().add(this);
       }
        return true;
    }
    public Date getTime(){
        return this.time;
    }

    public User getSender(){
        return this.sender;
    }
    public ArrayList<Comments> getComments(){
        return postComments;
    }
    public ArrayList<User> getLikes(){
        return this.likes;
    }
    public ArrayList<User> getDislikes(){
        return this.dislikes;
    }
}
