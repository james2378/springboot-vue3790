<template>
	<div class="diy_list page_tourist_route" id="tourist_route_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">旅游路线列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="路线行程搜索" v-model="query['route_travel']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="景点类别搜索" v-model="query['scenic_spot_category']" />
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
								<b-dropdown text="景点类别" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','scenic_spot_category')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_scenic_spot_category" :key="i" @click="filter_set(o['scenic_spot_category'],'scenic_spot_category')" >
												{{ o['scenic_spot_category'] }}
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
						<list_tourist_route :list="list" />
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
	import list_tourist_route from "@/components/diy/list_tourist_route.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_tourist_route
		},
		data() {
			return {
				url_get_list: "~/api/tourist_route/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"route_travel": "", // 路线行程
					"scenic_spot_category": "", // 景点类别
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
						name: "路线行程正序",
						value: "`route_travel` asc",
					},
					{
						name: "路线行程倒序",
						value: "`route_travel` desc",
					},
					{
						name: "景点类别正序",
						value: "`scenic_spot_category` asc",
					},
					{
						name: "景点类别倒序",
						value: "`scenic_spot_category` desc",
					},
				],
				// 景点类别列表
				"list_scenic_spot_category": [],
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
			 * 获取景点类别列表
			 */
			async get_list_scenic_spot_category() {
				var json = await this.$get("~/api/classification_of_scenic_spots/get_list?");
				if (json.result) {
					this.list_scenic_spot_category = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
			filter_scenic_spot_category(o) {
				if (o == "全部") {
					this.query["scenic_spot_category"] = "";
				} else {
					this.query["scenic_spot_category"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.route_travel = ""
				this.query.scenic_spot_category = ""
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
			 * 获取景点类别列表
			 */
			this.get_list_scenic_spot_category();
		}
	}
</script>

<style>
</style>
