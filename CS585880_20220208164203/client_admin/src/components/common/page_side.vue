<template>
	<el-menu class="side el-menu-vertical-demo" default-active="/" @open="handleOpen" @close="handleClose"
		background-color="#545c64" text-color="#fff" active-text-color="#38f" :collapse="isCollapse" :router="true">
		<el-menu-item index="/">
			<i class="el-icon-s-home"></i>
			<span slot="title">后台首页</span>
		</el-menu-item>
		<el-submenu index="web"
			v-show="user_group == '管理员' || $check_group(['/nav/table','/link/table','/notice/table','/slides/table','/ad/table','/message/table'])">
			<template slot="title"><i class="el-icon-data-board"></i><span>公告内容</span></template>
			<!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/auth/table')" index="/auth/table">
				<span>权限管理</span>
			</el-menu-item> -->
			<!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/link/table')" index="/link/table">
				<span>友情链接</span>
			</el-menu-item> -->
			<el-menu-item v-show="user_group == '管理员' || $check_action('/slides/table')" index="/slides/table">
				<span>轮播图</span>
			</el-menu-item>
			<!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/ad/table')" index="/ad/table">
				<span>广告</span>
			</el-menu-item> -->
			<el-menu-item v-show="user_group == '管理员' || $check_action('/notice/table')" index="/notice/table">
				<span>公告栏</span>
			</el-menu-item>
		</el-submenu>

		<el-submenu index="user"
			v-show="user_group == '管理员' || $check_group(['/user/table','/user_group/table','/auth/table'
			,'/registered_user/table'
			])">
			<template slot="title"><i class="el-icon-user-solid"></i><span>系统用户</span></template>
			<el-menu-item index="/user/table" v-show="user_group == '管理员'"><span>管理员</span></el-menu-item>
			<el-menu-item index="/registered_user/table" v-show="user_group == '管理员' || $check_action('/registered_user/table')"><span>注册用户</span></el-menu-item>
			<!-- <el-menu-item index="/user_group/table"><span>用户组</span></el-menu-item> -->
		</el-submenu>
		<el-submenu index="content"
			v-show="$check_group(['/forum/table','/forum_type/table','/article/table','/article_type/table','/exam/table'])">
			<template slot="title"><i class="el-icon-chat-line-round"></i><span>内容模块</span></template>
			<el-menu-item v-show="user_group == '管理员' || $check_action('/forum/table')" index="/forum/table">
				<!-- <span>论坛</span> -->
				<span>{{$page_title("/forum/table") || "论坛"}}</span>
			</el-menu-item>
			<el-menu-item v-show="user_group == '管理员' || $check_action('/forum_type/table')" index="/forum_type/table">
				<!-- <span>论坛类型</span> -->
				<span>{{$page_title("/forum_type/table") || "论坛分类"}}</span>
			</el-menu-item>
			<el-menu-item v-show="user_group == '管理员' || $check_action('/article/table')" index="/article/table">
				<!-- <span>文章</span> -->
				<span>{{$page_title("/article/table") || "文章"}}</span>
			</el-menu-item>
			<el-menu-item v-show="user_group == '管理员' || $check_action('/article_type/table')"
				index="/article_type/table">
				<!-- <span>文章类型</span> -->
				<span>{{$page_title("/article_type/table") || "文章分类"}}</span>
			</el-menu-item>
		</el-submenu>

		<el-submenu index="more" v-show="auth.length">
			<template slot="title"><i class="el-icon-copy-document"></i><span>模块管理</span></template>
			<el-menu-item v-for="(o, idx) in auth" v-show="$check_action(o.path)" :index="o.path">
				<span>{{o.page_title}}</span>
			</el-menu-item>
		</el-submenu>
	</el-menu>
</template>

<script>
	export default {
		props: {
			isCollapse: {
				trye: Boolean,
				default: false
			}
		},
		data: function() {
			return {
				user_group: this.$store.state.user.user_group
			}
		},
		methods: {
			handleOpen(key, keyPath) {
				// console.log(key, keyPath);
			},

			handleClose(key, keyPath) {
				// console.log(key, keyPath);
			},

		},
		computed: {
			auth() {
				var tables = this.$store.state.web.auth;
				var lt = [];
				for (var i = 0; i < tables.length; i++) {
					var o = tables[i];
					if (o.path.indexOf("/table") !== -1) {
						let list = ["exam","article", "article_type",,"comment","forum","forum_type"];
						list.push("registered_user");
						if (list.indexOf(o.table_name) === -1) {
							lt.push(o);
						}
					}
				}
				return lt;
			}
		}
	}
</script>


<style>
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		width: 16rem;
	}

	.side {
		float: left;
		background: #545c64;
		color: #fff;
		overflow-y: scroll;
		height: 100vh;
	}
	.side::-webkit-scrollbar{
		display: none;
	}
.page .side .el-submenu .el-menu .el-menu-item{
	padding-left: 52px!important;
}
.page .side .el-submenu .el-menu .el-menu-item:hover{
	font-size: 17px;
}
.page .side .el-submenu .el-menu .is-active{
	background: #333!important;
	color: #fff!important;
}
.page .side .el-submenu .el-menu{
	display: block;
}
.page .side .el-submenu .el-submenu__title,
.page .side .el-submenu:nth-child(odd) .el-menu .el-menu-item{
	/* background: rgb(53, 54, 63)!important; */
}
.page .side .el-submenu .el-menu .is-active{
	background: #000!important;
}
.page .side .el-submenu .el-menu .el-menu-item:hover{
	font-size: 14px;
}
.page .side .el-submenu .el-submenu__title,
.page .side .el-submenu .el-icon-arrow-down{
	/* display: none; */
}
.page .side .el-submenu .el-menu:nth-last-child(1){
	/* display: none!important; */
}
.page .side	.el-submenu:nth-last-child(1) .el-menu{
		display: block!important;;
	}
	.el-submenu .el-menu .el-menu-item:hover{
		font-size: 20px;
		color: rgb(83, 98, 235)!important;
	}

.page .side .el-submenu:nth-last-child(1) .el-icon-arrow-down{
	display: none;
}
.page .side .el-submenu:nth-last-child(1) .el-menu{
		/* display:!important; */
	}

</style>