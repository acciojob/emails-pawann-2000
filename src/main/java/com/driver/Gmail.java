package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    private int inboxCapacity; //maximum number of mails inbox can store
    private ArrayList<Mail> inbox;//Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    private ArrayList<Mail> trash;//Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new ArrayList<>();
        this.trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.

        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size() >= getInboxCapacity()){
            trash.add(inbox.get(0));
            inbox.remove(0);
        }
        inbox.add(new Mail(date, sender, message));
    }

//    private void moveOldestToTrash(){
//        Mail oldest = inbox.get(0);
//        for(Mail mail : inbox){
//            if(mail.date.before(oldest.date)) {
//                oldest = mail;
//            }
//        }
//        inbox.remove(oldest);
//        trash.add(oldest);
//    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Mail mail : inbox){
            if(mail.message.equals(message)) {
                inbox.remove(mail);
                trash.add(mail);
                break;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.isEmpty()){
            return null;
        }
        return inbox.get(inbox.size()-1).message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.isEmpty()){
            return null;
        }
        return inbox.get(0).message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        int count=0;
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        for(Mail mail : inbox){
            if(mail.date.compareTo(start) >= 0 && mail.date.compareTo(end) <= 0){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }

    private static class Mail {
        Date date;
        String sender;
        String message;

        Mail(Date date, String sender, String message){
            this.date = date;
            this.sender = sender;
            this.message = message;
        }
    }
}
