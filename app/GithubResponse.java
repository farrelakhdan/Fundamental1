public class GithubResponse{

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("incomplete_results")
	private boolean incompleteResults;

	@SerializedName("items")
	private List<ItemsItem> items;

	public int getTotalCount(){
		return totalCount;
	}

	public boolean isIncompleteResults(){
		return incompleteResults;
	}

	public List<ItemsItem> getItems(){
		return items;
	}
}
