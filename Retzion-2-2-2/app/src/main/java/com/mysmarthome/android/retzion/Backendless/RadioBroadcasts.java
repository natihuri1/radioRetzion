
package com.mysmarthome.android.retzion.Backendless;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class RadioBroadcasts
{
  private java.util.Date updated;
  private String objectId;
  private java.util.Date created;
  private String Broadcast;
  private String ownerId;
  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getBroadcast()
  {
    return Broadcast;
  }

  public void setBroadcast( String Broadcast )
  {
    this.Broadcast = Broadcast;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

                                                    
  public RadioBroadcasts save()
  {
    return Backendless.Data.of( RadioBroadcasts.class ).save( this );
  }

  public void saveAsync( AsyncCallback<RadioBroadcasts> callback )
  {
    Backendless.Data.of( RadioBroadcasts.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( RadioBroadcasts.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( RadioBroadcasts.class ).remove( this, callback );
  }

  public static RadioBroadcasts findById( String id )
  {
    return Backendless.Data.of( RadioBroadcasts.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<RadioBroadcasts> callback )
  {
    Backendless.Data.of( RadioBroadcasts.class ).findById( id, callback );
  }

  public static RadioBroadcasts findFirst()
  {
    return Backendless.Data.of( RadioBroadcasts.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<RadioBroadcasts> callback )
  {
    Backendless.Data.of( RadioBroadcasts.class ).findFirst( callback );
  }

  public static RadioBroadcasts findLast()
  {
    return Backendless.Data.of( RadioBroadcasts.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<RadioBroadcasts> callback )
  {
    Backendless.Data.of( RadioBroadcasts.class ).findLast( callback );
  }

  public static List<RadioBroadcasts> find( DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( RadioBroadcasts.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<RadioBroadcasts>> callback )
  {
    Backendless.Data.of( RadioBroadcasts.class ).find( queryBuilder, callback );
  }
}