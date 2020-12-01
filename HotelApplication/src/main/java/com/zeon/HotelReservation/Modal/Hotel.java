package com.zeon.HotelReservation.Modal;

import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;




@Entity(name = "hoteldb")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String type;
	private String name;
	private int ratting;
	private String cityCode;
	private float latitude;
	private float longtitude;
	private AddressHotel address;
	private Contact contact;
	@Lob
	private String description;
	@Embedded
	@ElementCollection
	@JoinTable(name = "amenitiesdb")
	private Set<String> amenities = new HashSet<String>();
	@Embedded
	@ElementCollection
	@JoinTable(name = "mediadb")
	private Set<String> media = new HashSet<String>();
	private Boolean avalibale;
	@Embedded
	@ElementCollection
	@JoinTable(name = "roomListdb")
	private Set<Room> roomList = new HashSet<Room>();

	@Embedded
	@ElementCollection
	@JoinTable(name = "singleRoomListdb")
	private Set<SingleRoom> singleRoomList = new HashSet<SingleRoom>();

	@Embedded
	@ElementCollection
	@JoinTable(name = "dualRoomListdb")
	private Set<DualRoom> dualRoomList = new HashSet<DualRoom>();

	@Embedded
	@ElementCollection
	@JoinTable(name = "suitRoomListdb")
	private Set<SuitRoom> suitRoomList = new HashSet<SuitRoom>();

//	@ElementCollection
//	@JoinTable(name="suitRoomdb")
//	@GenericGenerator(name="sequence-gen",strategy="sequence")
//	@CollectionId(columns = { @Column(name="ROOM-Id") }, generator = "sequence-gen", type = @Type(type = "long"))
//	private Collection<SuitRoom> suitRoomList = new ArrayList<SuitRoom>();
//	
//	
//	@ElementCollection
//	@JoinTable(name="dualRoomdb")
//	@GenericGenerator(name="dsequence-gen",strategy="sequence")
//	@CollectionId(columns = { @Column(name="ROOM-ID") }, generator = "dsequence-gen", type = @Type(type = "long"))
//	private Collection<DualRoom> dualRoomList = new ArrayList<DualRoom>();
//	
//	
//	@ElementCollection
//	@JoinTable(name="singleRoomdb")
//	@GenericGenerator(name="gsequence-gen",strategy="sequence")
//	@CollectionId(columns = { @Column(name="ROOM-ID") }, generator = "gsequence-gen", type = @Type(type = "long"))
//	private Collection<SingleRoom> singleRoomList = new ArrayList<SingleRoom>();

	public Hotel() {

	}

	public Hotel(Long id, String type, String name, int ratting, String cityCode, float latitude, float longtitude,
			AddressHotel address, Contact contact, String description, Set<String> amenities, Set<String> media,
			Boolean avalibale, Set<Room> roomList) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.ratting = ratting;
		this.cityCode = cityCode;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.address = address;
		this.contact = contact;
		this.description = description;
		this.amenities = amenities;
		this.media = media;
		this.avalibale = avalibale;
		this.roomList = roomList;
	}

	public Hotel(long id, String type, String name, int ratting, String cityCode, float latitude, float longtitude,
			AddressHotel address, Contact contact, String description, Set<String> amenities, Set<String> media,
			Boolean avalibale, Set<Room> roomList, Set<SingleRoom> singleRoomList, Set<DualRoom> dualRoomList,
			Set<SuitRoom> suitRoomList) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.ratting = ratting;
		this.cityCode = cityCode;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.address = address;
		this.contact = contact;
		this.description = description;
		this.amenities = amenities;
		this.media = media;
		this.avalibale = avalibale;
		this.roomList = roomList;
		this.singleRoomList = singleRoomList;
		this.dualRoomList = dualRoomList;
		this.suitRoomList = suitRoomList;
	}

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRatting() {
		return ratting;
	}

	public void setRatting(int ratting) {
		this.ratting = ratting;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
	}

	public AddressHotel getAddress() {
		return address;
	}

	public void setAddress(AddressHotel address) {
		this.address = address;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<String> amenities) {
		this.amenities = amenities;
	}

	public void setAmenitiesIndex(String amenitie) {
		amenities.add(amenitie);
	}

	public Set<String> getMedia() {
		return media;
	}

	public void setMedia(Set<String> media) {
		this.media = media;
	}

	public void setMediaIndex(String medias) {
		media.add(medias);
	}

	public Boolean getAvalibale() {
		return avalibale;
	}

	public void setAvalibale(Boolean avalibale) {
		this.avalibale = avalibale;
	}

	public Set<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(Set<Room> roomList) {
		this.roomList = roomList;
	}

	public Set<SingleRoom> getSingleRoomList() {
		return singleRoomList;
	}

	public void setSingleRoomList(Set<SingleRoom> singleRoomList) {
		this.singleRoomList = singleRoomList;
	}

	public Set<DualRoom> getDualRoomList() {
		return dualRoomList;
	}

	public void setDualRoomList(Set<DualRoom> dualRoomList) {
		this.dualRoomList = dualRoomList;
	}

	public Set<SuitRoom> getSuitRoomList() {
		return suitRoomList;
	}

	public void setSuitRoomList(Set<SuitRoom> suitRoomList) {
		this.suitRoomList = suitRoomList;
	}

//	public Collection<SuitRoom> getSuitRoomList() {
//		return suitRoomList;
//	}
//
//	public void setSuitRoomList(Collection<SuitRoom> suitRoomList) {
//		this.suitRoomList = suitRoomList;
//	}
//
//	public Collection<DualRoom> getDualRoomList() {
//		return dualRoomList;
//	}
//
//	public void setDualRoomList(Collection<DualRoom> dualRoomList) {
//		this.dualRoomList = dualRoomList;
//	}
//
//	public Collection<SingleRoom> getSingleRoomList() {
//		return singleRoomList;
//	}
//
//	public void setSingleRoomList(Collection<SingleRoom> singleRoomList) {
//		this.singleRoomList = singleRoomList;
//	}

	public static Comparator<Hotel> HotelRattingComparator = new Comparator<Hotel>() {

		public int compare(Hotel h1, Hotel h2) {
			int ratting1 = h1.getRatting();
			int ratting2 = h2.getRatting();

			return ratting1 - ratting2;

		}
	};

	public static Comparator<Hotel> HotelRattingComparatorPro = new Comparator<Hotel>() {

		public int compare(Hotel h1, Hotel h2) {
			int ratting1 = h1.getRatting();
			int ratting2 = h2.getRatting();

			return ratting2 - ratting1;

		}

	};
	
    public static Comparator<Hotel> HotelNameComparator = new Comparator<Hotel>() {

    	public int compare(Hotel h1, Hotel h2) {
    	   String HotelName1 = h1.getName().toUpperCase();
    	   String HotelName2 = h2.getName().toUpperCase();

    	   return HotelName1.compareTo(HotelName2);

        }};
        
        public static Comparator<Hotel> HotelPriceComparator = new Comparator<Hotel>() {

        	public int compare(Hotel h1, Hotel h2) {
        		
        		ArrayList<SingleRoom> array1 = new ArrayList<SingleRoom>(h1.getSingleRoomList());
        		ArrayList<SingleRoom> array2 = new ArrayList<SingleRoom>(h2.getSingleRoomList());
        		float price1 = array1.get(0).getPrice();
        		float price2 = array2.get(0).getPrice();

         	   return (int) (price1 - price2) ;

            }};

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", type=" + type + ", name=" + name + ", ratting=" + ratting + ", cityCode="
				+ cityCode + ", latitude=" + latitude + ", longtitude=" + longtitude + ", address=" + address
				+ ", contact=" + contact + ", description=" + description + ", amenities=" + amenities + ", media="
				+ media + ", avalibale=" + avalibale + ", roomList=" + roomList + "]";
	}

}
