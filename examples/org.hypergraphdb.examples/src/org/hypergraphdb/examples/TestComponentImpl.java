/**
 *
 */
package org.hypergraphdb.examples;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HyperGraph;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author amivanoff
 *
 */
@Component
public class TestComponentImpl {

	static final String dbLocation = "./HGTestDB"; //$NON-NLS-1$

	@Activate
	public void start() {
		// with try-with-resources
		try (HyperGraph graph = new HyperGraph(dbLocation)) {
			HGHandle stringHandle, bookHandle, arrayHandle;

			String x = "Hello World"; //$NON-NLS-1$
			stringHandle = graph.add(x);

			Book myBook = new Book("Critique of Pure Reason", "E. Kant"); //$NON-NLS-1$ //$NON-NLS-2$
			bookHandle = graph.add(myBook);

			arrayHandle = graph.add(new double[] { 0.9, 0.1, 4.3434 });

			// myBook was created and previously added to the database.
			// Now, we will update one of the existing object's attributes:
			myBook.setYearPublished(1988);
			graph.update(myBook);

			// ...

			// Now, we need to delete the object from the database.
			graph.remove(bookHandle);
		}
		catch (Throwable t) {
			t.printStackTrace();
		}

		// without try-with-resources
		HyperGraph graph = new HyperGraph(dbLocation);
		try {
			String x = "Hello World 2"; //$NON-NLS-1$
			HGHandle stringHandle = graph.add(x);
		}
		finally {
			graph.close();
		}
	}
}
