package business_Logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Room_Catalog implements Serializable {

    public Vector<Room> rooms=new Vector<Room>(1);
    public Vector<Room> availabe_rooms=new Vector<Room>(1);
    public Vector<Room> booked_rooms=new Vector<Room>(1);
    Room_Catalog()
    {
        int room_no=1;
        for(int i=0;i<10;i++)
        {
            rooms.add(new Room(10000, room_no++, 3));
            availabe_rooms.add(new Room(10000, room_no-1, 3));
        }
        for(int i=0;i<10;i++)
        {
            rooms.add(new Room(8000, room_no++, 2));
            availabe_rooms.add(new Room(8000, room_no-1, 2));
        }
        for(int i=0;i<10;i++)
        {
            rooms.add(new Room(5000, room_no++, 1));
            availabe_rooms.add(new Room(5000, room_no-1, 1));
        }
        System.out.println("Rooms:"+rooms.size());
    }

    ArrayList<String> getAvailableRooms(int cap, double price)
    {

        ArrayList<String> available=new ArrayList<>();
        for(int i=0;i<availabe_rooms.size();i++)
        {
            if(availabe_rooms.elementAt(i).getCapacity()==cap && availabe_rooms.elementAt(i).getPrice()==price)
                available.add(availabe_rooms.elementAt(i).toString());
//			System.out.println(customers.elementAt(i).getName());
        }
        return available;

    }

    ArrayList<Room> getRooms()
    {

        ArrayList<Room> available=new ArrayList<>();
        for(int i=0;i<availabe_rooms.size();i++)
        {

            available.add(availabe_rooms.elementAt(i));
//			System.out.println(customers.elementAt(i).getName());
        }
        return available;

    }

    boolean bookRoom(String room_no)
    {

        //	ObservableList<String> available=FXCollections.observableArrayList();
        for(int i=0;i<availabe_rooms.size();i++)
        {
            if(availabe_rooms.elementAt(i).getRoom_no()==Integer.parseInt(room_no))
            {
                Room room=availabe_rooms.elementAt(i);
                if(availabe_rooms.remove(availabe_rooms.elementAt(i)))
                {
                    booked_rooms.add(room);
                    return true;
                }
            }
        }
        return false;

    }
    ArrayList<String> getPrices (int caps)
    {

        ArrayList<String> prices=new ArrayList<>();
        for(int i=0;i<availabe_rooms.size();i++)
        {
            if(availabe_rooms.elementAt(i).getCapacity()==caps)
                if(!prices.contains(Double.toString(availabe_rooms.elementAt(i).getPrice())))
                    prices.add(Double.toString(availabe_rooms.elementAt(i).getPrice()));
//			System.out.println(customers.elementAt(i).getName());
        }
        return prices;

    }

    Room getRoom(String room_no)
    {
        for(int i=0;i<rooms.size();i++)
        {
            if(rooms.elementAt(i).getRoom_no()==Integer.parseInt(room_no))
                return rooms.elementAt(i);
        }
        return null;
    }

    public void ChangeRooms(Room room1,Room room2)
    {
        availabe_rooms.remove(room2);
        booked_rooms.remove(room1);

        availabe_rooms.add(room1);
        booked_rooms.add(room2);
    }

    public void deleteRoom(Room room) {
        availabe_rooms.add(room);
        booked_rooms.remove(room);
    }
	/*
	public String getPrice(String room_no)
	{
		for(int i=0;i<rooms.size();i++)
		{
			if(rooms.elementAt(i).getRoom_no()==Integer.parseInt(room_no))
				return Double.toString(rooms.elementAt(i).getPrice());
		}
		return "";
	}
	*/
	/*
	public String getPrice(String room_no)
	{
		for(int i=0;i<rooms.size();i++)
		{
			if(rooms.elementAt(i).getRoom_no()==Integer.parseInt(room_no))
				return Double.toString(rooms.elementAt(i).getPrice());
		}
		return "";
	}
	*/
}
