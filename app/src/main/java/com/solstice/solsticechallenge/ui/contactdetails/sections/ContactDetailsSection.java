package com.solstice.solsticechallenge.ui.contactdetails.sections;

import static android.text.TextUtils.isEmpty;

public class ContactDetailsSection {

    private String sectionName;
    private String sectionContent;
    private String sectionDescription;

    public ContactDetailsSection(String sectionName, String sectionContent) {
        this(sectionName, sectionContent, null);
    }

    public ContactDetailsSection(String sectionName, String sectionContent, String sectionDescription) {
        this.sectionName = sectionName;
        this.sectionContent = sectionContent;
        this.sectionDescription = sectionDescription;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getSectionContent() {
        return sectionContent;
    }

    public String getSectionDescription() {
        return sectionDescription;
    }

    public boolean hasSectionDescription() {
        return !isEmpty(sectionDescription);
    }
}
