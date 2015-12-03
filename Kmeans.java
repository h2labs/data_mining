import java.util.ArrayList;
import java.util.List;

public class Kmeans {
	public static void main(String[] args) {
		Kmeans kmeans = new Kmeans();
		int[] originalData = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,450,451,452,453,454,455,456,457,458,459,460,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478,479,480,481,482,483,484,485,486,487,488,489,490,491,492,493,494,495,496,497,498,499,500,501,502,503,504,505,506,507,508,509,510,511,512,513,514,515,516,517,518,519,520,521,522,523,524,525,526,527,528,529,530,531,532,533,534,535,536,537,538,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,554,555,556,557,558,559,560,561,562,563,564,565,566,567,568,569,570,571,572,573,574,575,576,577,578,579,580,581,582,583,584,585,586,587,588,589,590,591,592,593,594,595,596,597,598,599,600,601,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,644,645,646,647,648,649,650,651,652,653,654,655,656,657,658,659,660,661,662,663,664,665,666,667,668,669,670,671,672,673,674,675,676,677,678,679,680,681,682,683,684,685,686,687,688,689,690,691,692,693,694,695,696,697,698,699,700,950,951,952,953,954,955,956,957,958,959,960,961,962,963,964,965,966,967,968,969,970,971,972,973,974,975,976,977,978,979,980,981,982,983,984,985,986,987,988,989,990,991,992,993,994,995,996,997,998,999,1000 };
		int clusterNum = 10;
		List<Cluster> result = kmeans.initiateCluster(originalData, clusterNum);
		while (kmeans.isChanged(result)) {
			kmeans.printResult(result);
		}

	}

	List<Cluster> initiateCluster(int[] originalData, int clusterNum) {
		List<Cluster> result = new ArrayList<Cluster>();

		int dataCount = originalData.length;
		int[] clusterNumber = new int[dataCount];

		// Initial cluster setting
		for (int i = 0; i < dataCount; i++) {
			clusterNumber[i] = (int) (Math.random() * clusterNum);
		}
		for (int i = 0; i < clusterNum; i++) {
			Cluster cluster = new Cluster();
			cluster.number = i;
			result.add(cluster);
		}
		for (int i = 0; i < dataCount; i++) {
			result.get(clusterNumber[i]).members.add(originalData[i]);
		}
		return result;
	}

	boolean isChanged(List<Cluster> clusterList) {
		for (int i = 0; i < clusterList.size(); i++) {
			Cluster c = clusterList.get(i);
			List<Integer> members = c.members;
			for (int j = 0; j < members.size(); j++) {
				double min = Math.abs(c.getAverage() - members.get(j));
				int minClusterNumber = i;
				for (int k = 0; k < clusterList.size(); k++) {
					if (min > Math.abs(clusterList.get(k).getAverage()
							- members.get(j))) {
						min = Math.abs(clusterList.get(k).getAverage()
								- members.get(j));
						minClusterNumber = k;
					}
				}
				if (minClusterNumber != i) {
					clusterList.get(minClusterNumber).members.add(members
							.get(j));
					members.remove(j);
					return true;
				}
			}
		}
		return false;
	}

	void printResult(List<Cluster> clusterList) {
		for (Cluster c : clusterList) {
			System.out.println(c.number + ":" + c.getAverage());
			System.out.print("\t");
			for (int i : c.members) {
				System.out.print(i + ",");
			}
			System.out.println();
		}
		System.out.println("---------------");
	}
}

class Cluster {
	public int number;
	public List<Integer> members;

	public Cluster() {
		members = new ArrayList<Integer>();
	}

	public double getAverage() {
		int sum = 0;
		for (int i = 0; i < members.size(); i++) {
			sum += members.get(i);
		}
		return (double) sum / members.size();
	}
}
