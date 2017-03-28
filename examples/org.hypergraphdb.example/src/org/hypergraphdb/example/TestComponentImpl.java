/**
 *
 */
package org.hypergraphdb.example;


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
		HyperGraph graph = null;

		try {
			graph = new HyperGraph(dbLocation);

			HGHandle stringHandle, bookHandle, arrayHandle;

			String x = "Hello World"; //$NON-NLS-1$
			stringHandle = graph.add(x);

			Book mybook = new Book("Critique of Pure Reason", "E. Kant"); //$NON-NLS-1$ //$NON-NLS-2$
			bookHandle = graph.add(mybook);

			arrayHandle = graph.add(new double[] { 0.9, 0.1, 4.3434 });

			// myBook was created and previously added to the database.
			// Now, we will update one of the existing object's attributes:
			mybook.setYearPublished(1988);
			graph.update(mybook);

			// ...

			// Now, we need to delete the object from the database.
			graph.remove(bookHandle);

		}
		catch (Throwable t) {
			t.printStackTrace();
		}
		finally {
			graph.close();
		}
	}
}
