
package com.example.signlanguagetranslator.customView;

import com.example.signlanguagetranslator.tfLite.Classifier.Recognition;

import java.util.List;

public interface ResultsView {
    public void setResults(final List<Recognition> results);
}
