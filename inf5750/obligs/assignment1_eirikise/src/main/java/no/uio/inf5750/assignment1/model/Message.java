package no.uio.inf5750.assignment1.model;


public class Message
{
    private int id;
    public Message()
    {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    private String content;

}