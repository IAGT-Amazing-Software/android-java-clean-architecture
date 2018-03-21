package com.innopro.android.sample.presentation.view;


import com.innopro.android.sample.domain.Token;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link String}.
 */
public interface TokenView extends LoadDataView {
    /**
     * Render a message list in the UI.
     *
     * @param token The collection of {@link String} that will be shown.
     */
    void renderToken(Token token);

}
