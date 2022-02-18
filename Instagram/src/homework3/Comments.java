
package homework3;
import java.util.ArrayList;
import java.util.Date;

public class Comments {
    private String commentContent;
    private User sender;
    private Posts post;
    private ArrayList<User> likes= new ArrayList<>();
    private ArrayList<User> dislikes= new ArrayList<>();
    public Comments(User sender, Posts postWhichDoingComment, String commentContent ){
        this.sender=sender;
        this.post=postWhichDoingComment;
        this.commentContent=commentContent;
    }
    public boolean sendComment(){
        return this.post.getComments().add(this);
    }
    public String getCommentContent(){
        return this.commentContent;
    }
    public User getSender(){
        return this.sender;
    }
    public ArrayList<User> getLikes(){
        return this.likes;
    }
    public ArrayList<User> getDislikes(){
        return this.dislikes;
    }
}
