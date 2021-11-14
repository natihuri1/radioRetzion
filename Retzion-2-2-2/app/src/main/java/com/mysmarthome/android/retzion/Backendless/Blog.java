
package com.mysmarthome.android.retzion.Backendless;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class Blog
{
  private String objectId;
  private String Description;
  private String Name;
  private java.util.Date updated;
  private String Story;
  private java.util.Date created;
  private String ownerId;
  private java.util.Date DateOfStory;
  public String getObjectId()
  {
    return objectId;
  }

  public String getDescription()
  {
    return Description;
  }

  public void setDescription( String Description )
  {
    this.Description = Description;
  }

  public String getName()
  {
    return Name;
  }

  public void setName( String Name )
  {
    this.Name = Name;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getStory()
  {
    return Story;
  }

  public void setStory( String Story )
  {
    this.Story = Story;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getDateOfStory()
  {
    return DateOfStory;
  }

  public void setDateOfStory( java.util.Date DateOfStory )
  {
    this.DateOfStory = DateOfStory;
  }

                                                    
  public Blog save()
  {
    return Backendless.Data.of( Blog.class ).save( this );
  }

  public void saveAsync( AsyncCallback<Blog> callback )
  {
    Backendless.Data.of( Blog.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Blog.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Blog.class ).remove( this, callback );
  }

  public static Blog findById( String id )
  {
    return Backendless.Data.of( Blog.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<Blog> callback )
  {
    Backendless.Data.of( Blog.class ).findById( id, callback );
  }

  public static Blog findFirst()
  {
    return Backendless.Data.of( Blog.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<Blog> callback )
  {
    Backendless.Data.of( Blog.class ).findFirst( callback );
  }

  public static Blog findLast()
  {
    return Backendless.Data.of( Blog.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<Blog> callback )
  {
    Backendless.Data.of( Blog.class ).findLast( callback );
  }

  public static List<Blog> find( DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( Blog.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Blog>> callback )
  {
    Backendless.Data.of( Blog.class ).find( queryBuilder, callback );
  }
}