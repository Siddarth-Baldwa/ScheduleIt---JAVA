package com.example.register;

    public class ImageUploadInfo {

        private String imageName;
        private String imageURL;
        private String imageKey;

        public ImageUploadInfo(String name, String url, String key) {

            this.imageName = name;
            this.imageURL= url;
            this.imageKey = key;
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




