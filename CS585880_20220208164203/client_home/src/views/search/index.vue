<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="旅游资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="攻略交流"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/classification_of_scenic_spots/list', 'get')"
				:list="result_classification_of_scenic_spots_scenic_spot_category"
				title="景点分类景点类别"
				source_table="classification_of_scenic_spots"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registered_user/list', 'get')"
				:list="result_registered_user_user_name"
				title="注册用户用户名"
				source_table="registered_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/hotel_management/list', 'get')"
				:list="result_hotel_management_hotel_name"
				title="酒店管理酒店名称"
				source_table="hotel_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/hotel_management/list', 'get')"
				:list="result_hotel_management_layout_of_a_house_or_an_apartment"
				title="酒店管理房型"
				source_table="hotel_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/room_type_management/list', 'get')"
				:list="result_room_type_management_layout_of_a_house_or_an_apartment"
				title="房型管理房型"
				source_table="room_type_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/tourist_route/list', 'get')"
				:list="result_tourist_route_route_travel"
				title="旅游路线路线行程"
				source_table="tourist_route"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/tourist_route/list', 'get')"
				:list="result_tourist_route_scenic_spot_category"
				title="旅游路线景点类别"
				source_table="tourist_route"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_classification_of_scenic_spots_scenic_spot_category":[],
			"result_registered_user_user_name":[],
			"result_hotel_management_hotel_name":[],
			"result_hotel_management_layout_of_a_house_or_an_apartment":[],
			"result_room_type_management_layout_of_a_house_or_an_apartment":[],
			"result_tourist_route_route_travel":[],
			"result_tourist_route_scenic_spot_category":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取攻略交流
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取scenic_spot_category
	 */
	get_classification_of_scenic_spots_scenic_spot_category(){
		this.$get("~/api/classification_of_scenic_spots/get_list?like=0", { page: 1, size: 10, "scenic_spot_category": this.query.word }, (json) => {
		  if (json.result) {
			var result_classification_of_scenic_spots_scenic_spot_category = json.result.list;
			result_classification_of_scenic_spots_scenic_spot_category.map(o => o.title = o['scenic_spot_category'])
	  			this.result_classification_of_scenic_spots_scenic_spot_category = result_classification_of_scenic_spots_scenic_spot_category
		 	}
		});
	},
	/**
	 * 获取user_name
	 */
	get_registered_user_user_name(){
		this.$get("~/api/registered_user/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_user_user_name = json.result.list;
			result_registered_user_user_name.map(o => o.title = o['user_name'])
	  			this.result_registered_user_user_name = result_registered_user_user_name
		 	}
		});
	},
	/**
	 * 获取hotel_name
	 */
	get_hotel_management_hotel_name(){
		this.$get("~/api/hotel_management/get_list?like=0", { page: 1, size: 10, "hotel_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_hotel_management_hotel_name = json.result.list;
			result_hotel_management_hotel_name.map(o => o.title = o['hotel_name'])
	  			this.result_hotel_management_hotel_name = result_hotel_management_hotel_name
		 	}
		});
	},
	/**
	 * 获取layout_of_a_house_or_an_apartment
	 */
	get_hotel_management_layout_of_a_house_or_an_apartment(){
		this.$get("~/api/hotel_management/get_list?like=0", { page: 1, size: 10, "layout_of_a_house_or_an_apartment": this.query.word }, (json) => {
		  if (json.result) {
			var result_hotel_management_layout_of_a_house_or_an_apartment = json.result.list;
			result_hotel_management_layout_of_a_house_or_an_apartment.map(o => o.title = o['layout_of_a_house_or_an_apartment'])
	  			this.result_hotel_management_layout_of_a_house_or_an_apartment = result_hotel_management_layout_of_a_house_or_an_apartment
		 	}
		});
	},
	/**
	 * 获取layout_of_a_house_or_an_apartment
	 */
	get_room_type_management_layout_of_a_house_or_an_apartment(){
		this.$get("~/api/room_type_management/get_list?like=0", { page: 1, size: 10, "layout_of_a_house_or_an_apartment": this.query.word }, (json) => {
		  if (json.result) {
			var result_room_type_management_layout_of_a_house_or_an_apartment = json.result.list;
			result_room_type_management_layout_of_a_house_or_an_apartment.map(o => o.title = o['layout_of_a_house_or_an_apartment'])
	  			this.result_room_type_management_layout_of_a_house_or_an_apartment = result_room_type_management_layout_of_a_house_or_an_apartment
		 	}
		});
	},
	/**
	 * 获取route_travel
	 */
	get_tourist_route_route_travel(){
		this.$get("~/api/tourist_route/get_list?like=0", { page: 1, size: 10, "route_travel": this.query.word }, (json) => {
		  if (json.result) {
			var result_tourist_route_route_travel = json.result.list;
			result_tourist_route_route_travel.map(o => o.title = o['route_travel'])
	  			this.result_tourist_route_route_travel = result_tourist_route_route_travel
		 	}
		});
	},
	/**
	 * 获取scenic_spot_category
	 */
	get_tourist_route_scenic_spot_category(){
		this.$get("~/api/tourist_route/get_list?like=0", { page: 1, size: 10, "scenic_spot_category": this.query.word }, (json) => {
		  if (json.result) {
			var result_tourist_route_scenic_spot_category = json.result.list;
			result_tourist_route_scenic_spot_category.map(o => o.title = o['scenic_spot_category'])
	  			this.result_tourist_route_scenic_spot_category = result_tourist_route_scenic_spot_category
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_classification_of_scenic_spots_scenic_spot_category();
		this.get_registered_user_user_name();
		this.get_hotel_management_hotel_name();
		this.get_hotel_management_layout_of_a_house_or_an_apartment();
		this.get_room_type_management_layout_of_a_house_or_an_apartment();
		this.get_tourist_route_route_travel();
		this.get_tourist_route_scenic_spot_category();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_classification_of_scenic_spots_scenic_spot_category();
	  this.get_registered_user_user_name();
	  this.get_hotel_management_hotel_name();
	  this.get_hotel_management_layout_of_a_house_or_an_apartment();
	  this.get_room_type_management_layout_of_a_house_or_an_apartment();
	  this.get_tourist_route_route_travel();
	  this.get_tourist_route_scenic_spot_category();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
