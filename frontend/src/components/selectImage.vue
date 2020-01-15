<template>
    <div>
        <label for="myImgFile" class="postImgLabel">Выбрать изображение</label>
        <input id="myImgFile" type="file" @change="onFileChange" class="postImg" accept="image/x-png,image/gif,image/jpeg"/>
        <div>
            <img v-if="imgUrl" :src="imgUrl" class="myImg"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "selectImage",
        props: ["imgUrl"],
        methods: {
            onFileChange(event) {
                if (event.target.files.length > 0) {
                    let file = event.target.files[0];
                    this.$emit('changeImageUrl', file);
                }
            }
        },
    }
</script>

<style scoped>
    .postImg{
        opacity: 0;
        width: 0px;
    }
    .myImg{
        max-width: 500px;
        margin-top: 10px;
    }
    .postImgLabel{
        padding: 5px 10px;
        border: 1px ridge black;
    }
</style>