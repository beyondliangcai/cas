package org.apereo.cas.web.view;

import org.apereo.cas.services.web.view.AbstractDelegatingCasView;
import org.apereo.cas.CasViewConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Renders and prepares CAS2 views. This view is responsible
 * to simply just prep the base model, and delegates to
 * a the real view to render the final output.
 * @author Misagh Moayyed
 * @since 4.1.0
 */
public class Cas20ResponseView extends AbstractDelegatingCasView {

    /**
     * Instantiates a new Abstract cas jstl view.
     */
    protected Cas20ResponseView() {
        super();
    }

    @Override
    protected void prepareMergedOutputModel(final Map<String, Object> model, final HttpServletRequest request,
                                            final HttpServletResponse response) throws Exception {
        super.putIntoModel(model, CasViewConstants.MODEL_ATTRIBUTE_NAME_PRINCIPAL, getPrincipal(model));
        super.putIntoModel(model, CasViewConstants.MODEL_ATTRIBUTE_NAME_CHAINED_AUTHENTICATIONS, getChainedAuthentications(model));
        super.putIntoModel(model, CasViewConstants.MODEL_ATTRIBUTE_NAME_PRIMARY_AUTHENTICATION, getPrimaryAuthenticationFrom(model));
    }


    /**
     * The type Success.
     */
    @RefreshScope
    @Component("cas2ServiceSuccessView")
    public static class Success extends Cas20ResponseView {
        /**
         * Instantiates a new Success.
         */
        public Success() {
            super();
        }

        @Autowired(required=false)
        @Override
        public void setView(@Qualifier("cas2SuccessView") final View view) {
            super.setView(view);
        }
    }
}
