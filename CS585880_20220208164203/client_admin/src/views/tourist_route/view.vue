<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','route_travel') || $check_field('add','route_travel') || $check_field('set','route_travel')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="路线行程" prop="route_travel">
					<el-input id="route_travel" v-model="form['route_travel']" placeholder="请输入路线行程"
							  v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','route_travel')) || (!form['tourist_route_id'] && $check_field('add','route_travel'))" :disabled="disabledObj['route_travel_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','route_travel')">{{form['route_travel']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','scenic_spot_category') || $check_field('add','scenic_spot_category') || $check_field('set','scenic_spot_category')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="景点类别" prop="scenic_spot_category">
					<el-select id="scenic_spot_category" v-model="form['scenic_spot_category']"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','scenic_spot_category')) || (!form['tourist_route_id'] && $check_field('add','scenic_spot_category'))">
						<el-option v-for="o in list_scenic_spot_category" :key="o['scenic_spot_category']" :label="o['scenic_spot_category']"
							:value="o['scenic_spot_category']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','scenic_spot_category')">{{form['scenic_spot_category']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','days') || $check_field('add','days') || $check_field('set','days')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="天数" prop="days">
					<el-input id="days" v-model="form['days']" placeholder="请输入天数"
							  v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','days')) || (!form['tourist_route_id'] && $check_field('add','days'))" :disabled="disabledObj['days_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','days')">{{form['days']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover') || $check_field('add','cover') || $check_field('set','cover')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="封面" prop="cover">
					<el-upload :disabled="disabledObj['cover_isDisabled']" id="cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','cover')) || (!form['tourist_route_id'] && $check_field('add','cover'))">
						<img v-if="form['cover']" :src="$fullUrl(form['cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover'])" :preview-src-list="[$fullUrl(form['cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','price') || $check_field('add','price') || $check_field('set','price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="价格" prop="price">
					<el-input-number id="price" v-model.number="form['price']"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','price')) || (!form['tourist_route_id'] && $check_field('add','price'))"></el-input-number>
					<div v-else-if="$check_field('get','price')">{{form['price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cost_includes') || $check_field('add','cost_includes') || $check_field('set','cost_includes')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="费用包含" prop="cost_includes">
					<el-input type="textarea" id="cost_includes" v-model="form['cost_includes']" placeholder="请输入费用包含"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','cost_includes')) || (!form['tourist_route_id'] && $check_field('add','cost_includes'))" :disabled="disabledObj['cost_includes_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','cost_includes')">{{form['cost_includes']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','traffic_strategy') || $check_field('add','traffic_strategy') || $check_field('set','traffic_strategy')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="交通攻略" prop="traffic_strategy">
					<el-input type="textarea" id="traffic_strategy" v-model="form['traffic_strategy']" placeholder="请输入交通攻略"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','traffic_strategy')) || (!form['tourist_route_id'] && $check_field('add','traffic_strategy'))" :disabled="disabledObj['traffic_strategy_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','traffic_strategy')">{{form['traffic_strategy']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','accommodation_strategy') || $check_field('add','accommodation_strategy') || $check_field('set','accommodation_strategy')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="住宿攻略" prop="accommodation_strategy">
					<el-input type="textarea" id="accommodation_strategy" v-model="form['accommodation_strategy']" placeholder="请输入住宿攻略"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','accommodation_strategy')) || (!form['tourist_route_id'] && $check_field('add','accommodation_strategy'))" :disabled="disabledObj['accommodation_strategy_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','accommodation_strategy')">{{form['accommodation_strategy']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','food_introduction') || $check_field('add','food_introduction') || $check_field('set','food_introduction')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="美食攻略" prop="food_introduction">
					<el-input type="textarea" id="food_introduction" v-model="form['food_introduction']" placeholder="请输入美食攻略"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','food_introduction')) || (!form['tourist_route_id'] && $check_field('add','food_introduction'))" :disabled="disabledObj['food_introduction_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','food_introduction')">{{form['food_introduction']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','route_introduction') || $check_field('add','route_introduction') || $check_field('set','route_introduction')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="路线介绍" prop="route_introduction">
					<quill-editor v-model.number="form['route_introduction']"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','route_introduction')) || (!form['tourist_route_id'] && $check_field('add','route_introduction')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','route_introduction')" v-html="form['route_introduction']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "tourist_route_id",
				url_add: "~/api/tourist_route/add?",
				url_set: "~/api/tourist_route/set?",
				url_get_obj: "~/api/tourist_route/get_obj?",
				url_upload: "~/api/tourist_route/upload?",

				query: {
					"tourist_route_id": 0,
				},

				form: {
					"route_travel":'', // 路线行程
					"scenic_spot_category":'', // 景点类别
					"days":'', // 天数
					"cover":'', // 封面
					"price":0, // 价格
					"cost_includes":'', // 费用包含
					"traffic_strategy":'', // 交通攻略
					"accommodation_strategy":'', // 住宿攻略
					"food_introduction":'', // 美食攻略
					"route_introduction":'', // 路线介绍
					"tourist_route_id": 0, // ID

				},
				disabledObj:{
					"route_travel_isDisabled": false,
					"scenic_spot_category_isDisabled": false,
					"days_isDisabled": false,
					"cover_isDisabled": false,
					"cost_includes_isDisabled": false,
					"traffic_strategy_isDisabled": false,
					"accommodation_strategy_isDisabled": false,
					"food_introduction_isDisabled": false,
					"route_introduction_isDisabled": false,
				},
				//景点类别选项列表
				list_scenic_spot_category: [],

			}
		},
		methods: {
			/**
			 * 获取景点类别列表
			 */
			async get_list_scenic_spot_category() {
				var json = await this.$get("~/api/classification_of_scenic_spots/get_list?");
				if(json.result && json.result.list){
					this.list_scenic_spot_category = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传封面
			 * @param {Object} param图片参数
			 */
			upload_cover(param){
				this.uploadFile(param.file, "cover");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/tourist_route/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/tourist_route/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/tourist_route/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/tourist_route/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/tourist_route/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_scenic_spot_category();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
