package bit.hallnj7.langtrainer;

public class Question
{

    public Question(){} //empty constructor

    private String noun;
    private String article;
    private String imageId;

    public String getArticle()
    {
        return article;
    } //returns the article to be used in other classes

    public String getImageId()
    {
        return imageId;
    } //returns the imageID to be used in other classes

        public Question(String n, String a, String i) //assigns each of the variables
        {
            noun = n;
            article = a;
            imageId = i;
        }
}
