package id.co.indoeskrim.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import id.co.indoeskrim.service.IServiceContainer;

/**
 * 
 * @author Cipta.Mahdiar
 *
 */

public abstract class MenuServiceContainer<T, P> implements IServiceContainer<T, P> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public final T getResponse() {
		/** step 1 : declare */
		P context = createContext();

		/** step 2 : validate */
		processValidationResult(validateBeforeProces(context));

		/** step 3 : process */
		process(context);

		/** step 4 : validate */
		processValidationResult(validateAfterProcess(context));

		/** step 5 : get entity for response */
		return getResponseEntity(context);
	}

	private void processValidationResult(Boolean validAccess) {
		if (!validAccess) {
			logger.info("error validation process");
		}
	}

	protected abstract T getResponseEntity(P context);

}
