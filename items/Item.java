package items;

public abstract class Item 
{
	private String itemName;
	private String itemDescription;
	
	public Item(String name, String description)
	{
		itemName = name;
		itemDescription = description;
	}
	
	public Item()
	{
		
	}
	
	public String getItemName()
	{
		return itemName;
	}
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	public String getItemDescription()
	{
		return itemDescription;
	}
	public void setItemDescription(String itemDescription)
	{
		this.itemDescription = itemDescription;
	}
	@Override
	public String toString()
	{
		return "[itemName=" + itemName + ", itemDescription=" + itemDescription + "]";
	}
	
	
}
