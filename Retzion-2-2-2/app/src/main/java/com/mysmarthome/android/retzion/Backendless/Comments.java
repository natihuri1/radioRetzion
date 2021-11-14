
package com.mysmarthome.android.retzion.Backendless;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class Comments
{
  private java.util.Date created;
  private java.util.Date updated;
  private String objectId;
  private java.util.Date DateOfComment;
  private String name;
  private String comment;
  private String ownerId;
  public java.util.Date getCreated()
  {
    return created;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getDateOfComment()
  {
    return DateOfComment;
  }

  public void setDateOfComment( java.util.Date DateOfComment )
  {
    this.DateOfComment = DateOfComment;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment( String comment )
  {
    this.comment = comment;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

                                                    
  public Comments save()
  {
    return Backendless.Data.of( Comments.class ).save( this );
  }

  public void saveAsync( AsyncCallback<Comments> callback )
  {
    Backendless.Data.of( Comments.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Comments.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Comments.class ).remove( this, callback );
  }

  public static Comments findById( String id )
  {
    return Backendless.Data.of( Comments.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<Comments> callback )
  {
    Backendless.Data.of( Comments.class ).findById( id, callback );
  }

  public static Comments findFirst()
  {
    return Backendless.Data.of( Comments.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<Comments> callback )
  {
    Backendless.Data.of( Comments.class ).findFirst( callback );
  }

  public static Comments findLast()
  {
    return Backendless.Data.of( Comments.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<Comments> callback )
  {
    Backendless.Data.of( Comments.class ).findLast( callback );
  }

  public static List<Comments> find( DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( Comments.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Comments>> callback )
  {
    Backendless.Data.of( Comments.class ).find( queryBuilder, callback );
  }
}