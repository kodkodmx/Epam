package com.epam.rd.contactbook;

public class Contact {

    private String contactName;
    private Email[] emails;
    private Social[] socialMedia;
    private ContactInfo phoneNumber;
    private int emailCount;
    private int socialMediaCount;
    private static final int MAX_EMAILS = 3;
    private static final int MAX_SOCIAL_MEDIA = 5;

    public Contact(String contactName) {
        if (contactName == null || contactName.isEmpty()) {
            throw new IllegalArgumentException("Contact name cannot be null or empty.");
        }
        this.contactName = contactName;
        this.emails = new Email[MAX_EMAILS];
        this.socialMedia = new Social[MAX_SOCIAL_MEDIA];
        this.emailCount = 0;
        this.socialMediaCount = 0;
    }

    public void rename(String newName) {
        if (newName != null && !newName.isEmpty()) {
            this.contactName = newName;
        }
    }

    public Email addEmail(String localPart, String domain) {
        if (emailCount < MAX_EMAILS) {
            Email email = new Email(localPart + "@" + domain);
            emails[emailCount++] = email;
            return email;
        }
        return null;
    }

    public Email addEpamEmail(String firstname, String lastname) {
        if (emailCount < MAX_EMAILS) {
            Email epamEmail = new Email(firstname + "_" + lastname + "@epam.com") {
                @Override
                public String getTitle() {
                    return "Epam Email";
                }
            };
            emails[emailCount++] = epamEmail;
            return epamEmail;
        }
        return null;
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (phoneNumber == null) {
            phoneNumber = new ContactInfo() {
                @Override
                public String getTitle() {
                    return "Tel";
                }

                @Override
                public String getValue() {
                    return "+" + code + " " + number;
                }
            };
            return phoneNumber;
        }
        return null;
    }

    public Social addTwitter(String twitterId) {
        if (socialMediaCount < MAX_SOCIAL_MEDIA) {
            Social twitter = new Social("Twitter", twitterId);
            socialMedia[socialMediaCount++] = twitter;
            return twitter;
        }
        return null;
    }

    public Social addInstagram(String instagramId) {
        if (socialMediaCount < MAX_SOCIAL_MEDIA) {
            Social instagram = new Social("Instagram", instagramId);
            socialMedia[socialMediaCount++] = instagram;
            return instagram;
        }
        return null;
    }

    public Social addSocialMedia(String title, String id) {
        if (socialMediaCount < MAX_SOCIAL_MEDIA) {
            Social social = new Social(title, id);
            socialMedia[socialMediaCount++] = social;
            return social;
        }
        return null;
    }

    public ContactInfo[] getInfo() {
        int totalInfoCount = 1 + (phoneNumber != null ? 1 : 0) + emailCount + socialMediaCount;
        ContactInfo[] infoArray = new ContactInfo[totalInfoCount];
        int index = 0;
        infoArray[index++] = new NameContactInfo();

        if (phoneNumber != null) {
            infoArray[index++] = phoneNumber;
        }

        for (int i = 0; i < emailCount; i++) {
            infoArray[index++] = emails[i];
        }

        for (int i = 0; i < socialMediaCount; i++) {
            infoArray[index++] = socialMedia[i];
        }

        return infoArray;
    }

    private class NameContactInfo implements ContactInfo {
        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return contactName;
        }
    }

    public static class Email implements ContactInfo {
        private String email;

        public Email(String email) {
            this.email = email;
        }

        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return email;
        }
    }

    public static class Social implements ContactInfo {
        private String title;
        private String id;

        public Social(String title, String id) {
            this.title = title;
            this.id = id;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getValue() {
            return id;
        }
    }
}
