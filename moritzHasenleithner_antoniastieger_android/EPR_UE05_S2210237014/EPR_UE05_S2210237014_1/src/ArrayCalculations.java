import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayCalculations<assertEquals> {
	// calculations with arrays

	static int n;

	@Test
	public void testSmallestValue() {
		int[] a = new int[] { 2, -3, 5, 0 };
		assertEquals(getSmallestValue(a), -3);
		a = new int[] { -6, -6, -6 };
		assertEquals(getSmallestValue(a), -6);
		a = new int[] { 20, 41, 65, 17 };
		assertEquals(getSmallestValue(a), 17);
		a = new int[] { 46 };
		assertEquals(getSmallestValue(a), 46);

	}

	@Test
	public void testNegativeValues() {
		int[] a = new int[] { 7, 3, 1, 9 };
		assertEquals(getNumberOfNegativeValues(a), 0);
		a = new int[] { 4, -1, -59, -44 };
		assertEquals(getNumberOfNegativeValues(a), 3);
		a = new int[] { 3, -1, 100, 0 };
		assertEquals(getNumberOfNegativeValues(a), 1);
		a = new int[] { 2 };
		assertEquals(getNumberOfNegativeValues(a), 0);
	}

	@Test
	public void testMean() {
		int[] a = new int[] { -3, -40, -1, 0 };
		assertEquals(getMean(a), -11);
		a = new int[] { 4, 1, 9, 4 };
		assertEquals(getMean(a), 4.5);
		a = new int[] { 30, 100, 200 };
		assertEquals(getMean(a), 110);
		a = new int[] { 4 };
		assertEquals(getMean(a), 4);
	}

	@Test
	public void testArraySorted() {
		int[] a = new int[] { 8, 5, 3, 1 };
		assertTrue(isArraySorted(a));
		a = new int[] { 4, -1, -59, 44 };
		assertFalse(isArraySorted(a));
		a = new int[] { 1, 4, 7, 10, 80 };
		assertTrue(isArraySorted(a));
		a = new int[] { 6 };
		assertTrue(isArraySorted(a));
	}

	static int getSmallestValue(int[] a) {
		int min = a[0];

		for (int i = 0; i < a.length; i++) {
			if (a[i] < min) {
				min = a[i];
			}
		}
		return min;
	}

	static int getNumberOfNegativeValues(int[] a) {
		int countNeg = 0;

		for (int i = 0; i < a.length; i++) {
			if (a[i] < 0) {
				countNeg++;
			}
		}
		return countNeg;
	}

	static float getMean(int[] a) {
		float sum = 0;

		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum / a.length;
	}

	static boolean isArraySorted(int[] a) {
		boolean checkArray = true;

		// if array is only one value
		if (a.length == 1) {
			return true;
		}

		// check if upwards or downwards
		if (a[a.length - 1] > a[0]) {
			checkArray = true;
		} else { /* a[a.length - 1] <= a[0] */
			checkArray = false;
		}

		if (checkArray) { /* true */
			for (int i = 0; i < a.length - 1; i++) {
				if (a[i] < a[i + 1]) {
					checkArray = true;
				} else { /* a[i] >= a[i + 1] */
					return false;
				}
			}
		} else if (!checkArray) { /* false */
			for (int i = a.length - 1; i > 0; i--) {
				if (a[i] > a[i - 1]) {
					return false;
				} else { /* a[i] <= a[i - 1] */
					checkArray = true;
				}
			}
		}
		return checkArray;
	}

	public static void main(String[] args) {

		Out.print("Please enter your number of values: ");

		// declare and initialize variables
		int n = In.readInt();

		if (n > 0) {
			int[] a = new int[n];
			for (int i = 0; i < a.length; i++) {
				Out.print("Value " + i + ": ");
				a[i] = In.readInt();
			}

			// print header
			Out.println("Smallest value: " + getSmallestValue(a));
			Out.println("Number of negative values: " + getNumberOfNegativeValues(a));
			Out.println("Mean of all values: " + getMean(a));
			Out.print("The array is sorted: ");

			boolean sorted = isArraySorted(a);
			if (sorted) {
				Out.print("yes");
			} else { /* !sorted */
				Out.print("no");
			}

		} else { /* n <= 0 */
			Out.println("Invalid number of values!");
		}

	}
}
