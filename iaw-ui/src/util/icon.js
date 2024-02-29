import * as Icons from '@element-plus/icons-vue'
// 统一注册图标，key: i+图标名字
export function loadIcon(app) {
    // 注册ElementIcon
    for (const [key, component] of Object.entries(Icons)) {
        app.component(key, component)
    }
}