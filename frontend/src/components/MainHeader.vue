<template>
  <div class="nav-container">
    <v-app-bar absolute app color="white" hide-on-scroll elevation="0" height="80">


        <span class="topTitle hidden-md-and-down"></span>

        <router-link class="logo hidden-md-and-down" to="/" tag="span">
        </router-link>

        <router-link class="logoMini hidden-md-and-up" to="/" tag="span">
        </router-link>

        <v-toolbar-items class="hidden-md-and-down menus">
          <span @click="$router.push('/evinfo')">전기차 정보</span>
          <span @click="$router.push('/freeboardListPage')">어울림마당</span>
          <span @click="$router.push('/news')">전가치 뉴스</span>
          <span  @click="$router.push('/charingSearchServiceBoardList')">충전소찾기</span>
          <span  @click="$router.push('/parkingSearchServiceList')">주차장찾기</span>
          <span v-if="this.$store.state.auth =='관리자'" @click="$router.push('/memberManagement')">관리자페이지</span>
        </v-toolbar-items>

        <v-toolbar-items class="hidden-sm-and-down hidden-lg-and-up menusSmall">
          <span @click="$router.push('/evinfo')">전기차 정보</span>
          <span @click="$router.push('/freeboardListPage')">어울림마당</span>
          <span @click="$router.push('/news')">전가치 뉴스</span>
          <span v-if="this.$store.state.auth =='관리자'" @click="$router.push('/memberManagement')">관리자페이지</span>
          <span  @click="$router.push('/charingSearchServiceBoardList')">충전소찾기</span>
          <span  @click="$router.push('/parkingSearchServiceList')">주차장찾기</span>
          <span>원하는거 추가</span>
        </v-toolbar-items>

        <v-spacer></v-spacer>
        <v-toolbar-items class="memberItems">

          <div v-if="!LoginCheck" class="memberContainer hidden-sm-and-down">
            <span class="text" @click="$router.push('/loginPage')">로그인</span>
            <span class="text" @click="$router.push('/signUpPage')">회원가입</span>
          </div>

          <header-member-menu></header-member-menu>

        </v-toolbar-items>

        <!-- hamburger icon -->
        <!--        <v-app-bar-nav-icon @click="nav_drawer = !nav_drawer" :class="hamburger">-->
        <!--        </v-app-bar-nav-icon>-->

        <!-- nav-drawer if mobile -->
        <nav-drawer-component></nav-drawer-component>



    </v-app-bar>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import NavDrawerComponent from "./NavDrawerComponent";
import HeaderMemberMenu from "./header/HeaderMemberMenu";

export default {
  name: "MainHeader",
  components: {HeaderMemberMenu, NavDrawerComponent},
  mounted() {
  },
  computed: {
    ...mapState(['isLogin', 'session']),
    LoginCheck(){
      return this.isLogin
    }
  },
  data() {
    return {

    }
  },
  methods: {
    ...mapActions(['setIsLogin', 'logout']),
  },
}
</script>

<style scoped>

*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Montserrat", sans-serif;
  user-select: none;
}

a{
  text-decoration: none;
}

.topTitle{
  background-image: url("https://user-images.githubusercontent.com/83811729/141649050-85f58ee1-85d7-42cc-a276-1da6fc392508.png");
  background-size: contain;
  position: absolute;
  width: 300px;
  height: 100%;
  top: 0;
  left: 41%;
  opacity: 0.9;
}

.logo{
  position: absolute;
  top: 3px;
  left: 13px;
  cursor: pointer;
  background-image: url("https://raw.githubusercontent.com/KHWeb18/ESC/main/frontend/src/assets/logo/evslogo2.png");
  background-size: contain;
  width: 100%;
  height: 95%;
}

.logoMini{
  position: absolute;
  top: 2px;
  left: 40%;
  cursor: pointer;
  background-image: url("https://raw.githubusercontent.com/KHWeb18/ESC/main/frontend/src/assets/logo/evslogo2.png");
  background-size: contain;
  width: 100%;
  height: 98%;
}

.menus{
  z-index: 10;
  position: relative;
  align-items: center;
  left: 210px;
  top: 25px;
  max-height: 50px;
  font-size: 1.1em;
  font-weight: 600;
  cursor: pointer;
}
.menus:hover{
  color: #cfd6da;
}

.menus span{
  padding: 20px 30px;
  margin: 0 10px
}

.menus span:hover{
  color: black;
}

.menusSmall{
  z-index: 1;
  position: relative;
  top: 20px;
  left: 60px;
  max-height: 35px;
  font-size: 1.3em;
  font-weight: 600;
  cursor: pointer;
}
.menusSmall:hover{
  color: #cfd6da;
}

.menusSmall span{
  padding: 0 20px 0 0;
  margin: 0 20px 0 0;
}

.menusSmall span:hover{
  color: black;
}


.memberItems{
  position: absolute;
  top: 10px;
  right: 6%;
}

.memberContainer{
  font-size: 0.8em;
}
.memberContainer:hover{
  color: #cfd6da;
}
.memberContainer span:hover{
  color: black;
}

.text{
  font-size: 1em;
  margin-left: 25px;
  cursor: pointer;
}

.myPage{
  margin: 0 0 0 20px;
}
</style>
