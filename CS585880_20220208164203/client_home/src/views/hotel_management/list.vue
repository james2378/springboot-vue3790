<template>
	<div class="diy_list page_hotel_management" id="hotel_management_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">酒店管理列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="酒店名称搜索" v-model="query['hotel_name']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="房型搜索" v-model="query['layout_of_a_house_or_an_apartment']" />
							<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
								<b-dropdown text="房型" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','layout_of_a_house_or_an_apartment')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_layout_of_a_house_or_an_apartment" :key="i" @click="filter_set(o['layout_of_a_house_or_an_apartment'],'layout_of_a_house_or_an_apartment')" >
												{{ o['layout_of_a_house_or_an_apartment'] }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>
				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_hotel_management :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_hotel_management from "@/components/diy/list_hotel_management.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_hotel_management
		},
		data() {
			return {
				url_get_list: "~/api/hotel_management/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"hotel_name": "", // 酒店名称
					"layout_of_a_house_or_an_apartment": "", // 房型
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "`create_time` desc",
					},
					{
						name: "创建时间从低到高",
						value: "`create_time` asc",
					},
					{
						name: "更新时间从高到低",
						value: "`update_time` desc",
					},
					{
						name: "更新时间从低到高",
						value: "`update_time` asc",
					},
					{
						name: "酒店名称正序",
						value: "`hotel_name` asc",
					},
					{
						name: "酒店名称倒序",
						value: "`hotel_name` desc",
					},
					{
						name: "房型正序",
						value: "`layout_of_a_house_or_an_apartment` asc",
					},
					{
						name: "房型倒序",
						value: "`layout_of_a_house_or_an_apartment` desc",
					},
				],
				// 房型列表
				"list_layout_of_a_house_or_an_apartment": [],
			}
		},
		methods: {
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},
			/**
			 * 获取房型列表
			 */
			async get_list_layout_of_a_house_or_an_apartment() {
				var json = await this.$get("~/api/room_type_management/get_list?");
				if (json.result) {
					this.list_layout_of_a_house_or_an_apartment = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
			filter_layout_of_a_house_or_an_apartment(o) {
				if (o == "全部") {
					this.query["layout_of_a_house_or_an_apartment"] = "";
				} else {
					this.query["layout_of_a_house_or_an_apartment"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.hotel_name = ""
				this.query.layout_of_a_house_or_an_apartment = ""
				this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},
		},
		computed: {
		},
		created() {
			/**
			 * 获取房型列表
			 */
			this.get_list_layout_of_a_house_or_an_apartment();
		}
	}
</script>

<style>
</style>
