package no.uio.inf5750.assignment1.dao;

import no.uio.inf5750.assignment1.model.Message;


public interface MessageDao
{
    int save( Message message );
    Message get( int id );
    Message getLast();
}