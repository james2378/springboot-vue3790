<template>
	<div class="diy_home diy_list diy_hotel_management" id="diy_hotel_management_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/hotel_management/details?hotel_management_id=' + o['hotel_management_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/hotel_management/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/hotel_management/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','hotel_number')">酒店编号
					</th>
					<th class="diy_title" v-if="$check_field('get','hotel_name')">酒店名称
					</th>
					<th class="diy_title" v-if="$check_field('get','layout_of_a_house_or_an_apartment')">房型
					</th>
					<th class="diy_title" v-if="$check_field('get','price')">价格
					</th>
					<th class="diy_title" v-if="$check_field('get','photo')">照片
					</th>
					<th class="diy_title" v-if="$check_field('get','address')">地址
					</th>
					<th class="diy_title" v-if="$check_field('get','amenities')">便利设施
					</th>
					<th class="diy_title" v-if="$check_field('get','traffic_guide')">交通指南
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
					<td class="diy_field diy_text" v-if="$check_field('get','hotel_number')">
						<span>
							{{ o["hotel_number"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','hotel_name')">
						<span>
							{{ o["hotel_name"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','layout_of_a_house_or_an_apartment')">
						<span>
							{{ o["layout_of_a_house_or_an_apartment"] }}
						</span>
					</td>
					<td class="diy_field diy_number" v-if="$check_field('get','price')">
						<span>
							{{ o["price"] }}
						</span>
					</td>
					<td class="diy_field" v-if="$check_field('get','photo')">
						<img class="diy_img" :src="o['photo']" />
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','address')">
						<span>
							{{ o["address"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','amenities')">
						<span>
							{{ o["amenities"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','traffic_guide')">
						<span>
							{{ o["traffic_guide"] }}
						</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						{
							title: "照片",
							name: "photo",
							type: "图片",
							is_img_list: "1"
						},
						],
						itemList: [
								{
									title: "酒店编号",
									name: "hotel_number",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "酒店名称",
									name: "hotel_name",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "房型",
									name: "layout_of_a_house_or_an_apartment",
									type: "下寻",
									is_img_list: "1"
								},
								{
									title: "价格",
									name: "price",
									type: "数字",
									is_img_list: "1"
								},
								{
									title: "地址",
									name: "address",
									type: "文本",
									is_img_list: "0"
								},
						],
						richList: [
								{
									title: "便利设施",
									name: "amenities",
									type: "多文本"
								},
								{
									title: "交通指南",
									name: "traffic_guide",
									type: "多文本"
								},
								{
									title: "酒店介绍",
									name: "hotel_introduction",
									type: "编辑"
								},
						],
			};
		},
		methods: {
		},
		created() {
		},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

