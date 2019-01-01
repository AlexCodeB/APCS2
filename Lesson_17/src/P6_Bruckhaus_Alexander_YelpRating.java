
public class P6_Bruckhaus_Alexander_YelpRating implements Comparable<P6_Bruckhaus_Alexander_YelpRating> {
	private String target;
	private String review;
	private double rating;
	private String userName;

	@Override
	public int compareTo(P6_Bruckhaus_Alexander_YelpRating o) {
		return (int) (100 * o.getRating() - 100 * this.getRating());
	}
	public P6_Bruckhaus_Alexander_YelpRating(String target, String review, double rating, String userName) {
		super();
		this.target = target;
		this.review = review;
		this.rating = rating;
		this.userName = userName;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		String result = "Target:  " + this.target + "\n" +
						"Review:  " + this.review + "\n" +
						"Rating:  " + this.rating + "\n" +
						"User:    " + this.userName + "\n";
		return result;
	} 
}
