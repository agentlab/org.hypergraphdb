package hgtest.storage.bje.DefaultIndexImpl;

import static org.easymock.EasyMock.replay;

import org.hypergraphdb.HGException;
import org.hypergraphdb.storage.bje.DefaultIndexImpl;
import org.junit.Test;

public class DefaultIndexImpl_findTest extends DefaultIndexImplTestBasis
{
	@Test
	public void throwsException_whenIndexIsNotOpenedAhead() throws Exception
	{
		replay(mockedStorage);

		final DefaultIndexImpl<Integer, String> index = new DefaultIndexImpl<>(
				INDEX_NAME, mockedStorage, transactionManager, keyConverter,
				valueConverter, comparator, null);

		below.expect(HGException.class);
		below.expectMessage("Attempting to operate on index 'sample_index' while the index is being closed.");
		index.find(5);
	}

	@Test
	public void throwsException_whenKeyIsNull() throws Exception
	{
		startupIndex();

		try
		{
			below.expect(NullPointerException.class);
			index.find(null);
		}
		finally
		{
			index.close();
		}
	}

	@Test
	public void wrapsUnderlyingException_withHypergraphException() throws Exception
	{
		startupIndexWithFakeTransactionManager();

		try
		{
            below.expect(HGException.class);
            below.expectMessage("Failed to lookup index 'sample_index': java.lang.IllegalStateException: This exception is thrown by fake transaction manager.");
			index.find(2);
		}
		finally
		{
			index.close();
		}
	}
}
