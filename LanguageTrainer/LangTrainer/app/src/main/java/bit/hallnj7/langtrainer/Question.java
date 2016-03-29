package bit.hallnj7.langtrainer;

public class Question
{
        private String noun;
        private String article;

    public String getImageId()
    {
        return imageId;
    }

    private String imageId;

        public Question(String n, String a, String i)
        {
            noun = n;
            article = a;
            imageId = i;
        }
}
