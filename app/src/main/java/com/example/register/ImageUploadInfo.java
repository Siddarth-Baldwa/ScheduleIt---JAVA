package com.example.register;

    public class ImageUploadInfo {

        private String imageName;
        private String imageURL;
        private String imageKey;
        private String photovalue;

        public ImageUploadInfo(String name, String url, String key, String photovalue) {
            this.photovalue = photovalue;
            this.imageName = name;
            this.imageURL= url;
            this.imageKey = key;
        }

        public String getPhotovalue() {
            return photovalue;
        }

        public void setPhotovalue(String photovalue) {
            this.photovalue = photovalue;
        }

        public String getImageName() {

            return imageName;
        }
        public String getImageURL() {

            return imageURL;
        }

        public String getImageKey() {

            return imageKey;
        }

        public ImageUploadInfo(){

        }
    }




